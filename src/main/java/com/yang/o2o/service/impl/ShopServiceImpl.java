package com.yang.o2o.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yang.o2o.dao.ShopDao;
import com.yang.o2o.dto.ImageHolder;
import com.yang.o2o.dto.ShopExecution;
import com.yang.o2o.entity.Shop;
import com.yang.o2o.enums.ShopStateEnum;
import com.yang.o2o.service.ShopService;
import com.yang.o2o.util.ImageUtil;
import com.yang.o2o.util.PageCount;
import com.yang.o2o.util.PathUtil;

@Service
public class ShopServiceImpl implements ShopService{

	// 以成员变量的方式 注入进来
	@Autowired
	private ShopDao shopDao;
	
	@Transactional
	public ShopExecution addShop(Shop shop, ImageHolder thumbnail) {
		//首先判断店铺是否为空;为空时，返回ShopExecution对象，利用构造函数 调用枚举类里面的实例，
		if (shop == null) {
			return new ShopExecution(ShopStateEnum.NULL_SHOP);
		}
		
		try {
			//当有店铺添加时，先初始化店铺的状态、创建时间、修改时间
			shop.setStatus(0);
			shop.setShopCreateTime(new Date());
			shop.setShopAlterTime(new Date());
			
			//增加店铺信息
			int effectNum = shopDao.insertShop(shop);
			//先判断一下这一步的插入是否有效, 这里需要抛出 RuntimeException 它的主要作用是：一旦有问题 会将以前的操作取消，回滚到原来的初始状态。
			if (effectNum <= 0) {
				throw new RuntimeException("店铺创建失败");
			}else {
				//此时店铺插入成功，添加图片的地址
				if (thumbnail.getImage() != null) {
					//存储图片, 第一个参数 是shop实体对象，因为要用到对象id 创建图片的目录，第二个参数 是shopImg文件流 存储到相关的目录里面。
					// 可能会出错 用try包围
					try {
						addShopImg(shop, thumbnail);
					} catch (Exception e) {
						throw new RuntimeException("addShopImg error" + e.getMessage());
					}
				}
				// 更新店铺图片的地址
				effectNum = shopDao.updateShop(shop);
				if (effectNum <= 0) {
					throw new RuntimeException("店铺更新失败");
				}
			}
		} catch (Exception e) {
			throw new RuntimeException("增加店铺失败：" + e.getMessage());
		}
		
		//操作成功时，返回ShopExecution对象
		return new ShopExecution(ShopStateEnum.CHACK, shop);
	}

	private void addShopImg(Shop shop, ImageHolder thumbnail) {
		// 获取shop图片目录的相对值路径
		String dest = PathUtil.getShopImgPath(shop.getShopId());
		//然后存储图片，并返回相对值路径
		String shopImgAddr = ImageUtil.generateThumbnail(thumbnail, dest);
		shop.setShopImg(shopImgAddr);
	}

	@Override
	public Shop getByShopId(long shopId) {
		return shopDao.queryById(shopId);
	}

	@Transactional
	public ShopExecution modifyShop(Shop shop, ImageHolder thumbnail) {
		try {
			//先判断店铺是否为空
			if (shop == null || shop.getShopId() == null) {
				return new ShopExecution(ShopStateEnum.NULL_SHOP);
			}else {
				//主要分两步
				//第一步：判断是否需要更换图片,，如果需要更换图片，那就需要将原来的图片删除掉，在进行更换，需要个删除工具类进行操作
				if (thumbnail.getImage() != null && thumbnail.getImageName() != null && !"".equals(thumbnail.getImageName())) {
					Shop tempShop = shopDao.queryById(shop.getShopId());
					if (tempShop.getShopImg() != null) {
						ImageUtil.deleteFileOrPath(tempShop.getShopImg());
					}
					addShopImg(shop, thumbnail);
				}
				//第二步：如果需要 更新图片  更新店铺
				shop.setShopAlterTime(new Date());  //修改一下更新时间
				int effectNum = shopDao.updateShop(shop);
				
				if (effectNum <= 0) {
					return new ShopExecution(ShopStateEnum.INNER_ERROR);
				}else {
					shop = shopDao.queryById(shop.getShopId());
					return new ShopExecution(ShopStateEnum.SUCCESS, shop);
				}
			}
		} catch (Exception e) {
			throw new RuntimeException("更新店铺失败：" + e.getMessage());
		}
	}

	@Override
	public ShopExecution getShopList(Shop shopCondition, int pageIndex, int pageSize) {
		int rowIndex = PageCount.pageIndexToRow(pageIndex, pageSize); //获取从哪开始取数据
		List<Shop> shoplList = shopDao.queryShopList(shopCondition, rowIndex, pageSize); //获取店铺列表
		int shopNum = shopDao.queryShopCount(shopCondition);
		
		ShopExecution se = new ShopExecution();
		if (shoplList != null) {
			se.setShopList(shoplList);
			se.setShopCount(shopNum);
		}else {
			se.setStateInfo(ShopStateEnum.INNER_ERROR.getStateInfo());
		}
		return se;
	}

}
