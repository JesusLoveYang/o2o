package com.yang.o2o.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yang.o2o.dao.ProductDao;
import com.yang.o2o.dao.ProductImgDao;
import com.yang.o2o.dto.ImageHolder;
import com.yang.o2o.dto.ProductExecution;
import com.yang.o2o.entity.Product;
import com.yang.o2o.entity.ProductImg;
import com.yang.o2o.enums.ProductStateEnum;
import com.yang.o2o.service.ProductService;
import com.yang.o2o.util.ImageUtil;
import com.yang.o2o.util.PageCount;
import com.yang.o2o.util.PathUtil;

@Service
public class ProductServiceImpl implements ProductService{

	@Autowired
	private ProductDao productDao;
	
	@Autowired
	private ProductImgDao productImgDao;
	
	@Override
	@Transactional
	// 1.处理缩略图，获取缩略图相对路径并赋值给product
	// 2.往tb_product写入商品信息，获取productId
	// 3.结合productId批量处理商品详情图
	// 4.将商品详情图列表批量插入tb_product_img中
	public ProductExecution addProduct(Product product, ImageHolder thumbnail, List<ImageHolder> productImgHolderList) {
		//空值判断
		if (product != null && product.getShop() != null && product.getShop().getShopId() > 0) {
			// 给商品设置上默认属性
			product.setCreateTime(new Date());
			product.setAlterTime(new Date());
			
			// 默认为上架的状态
			product.setProductStatus(1);
			// 若商品缩略图不为空则添加
			if (thumbnail != null) {
				addThumbnail(product, thumbnail);
			}
			try {
				//添加商品和缩略图
				int effectedNum = productDao.insertProducts(product);
				if (effectedNum <= 0) {
					throw new RuntimeException("添加商品失败");
				}
			} catch (Exception e) {
				throw new RuntimeException("addProduct error:" + e.getMessage());
			}
			// 若商品详情图不为空则添加
			if (productImgHolderList != null && productImgHolderList.size() > 0) {
				addProductImgList(product, productImgHolderList);
			}
			return new ProductExecution(ProductStateEnum.SUCCESS, product);
		}else {
			// 传参为空则返回空值错误信息
			return new ProductExecution(ProductStateEnum.EMPTY);
		}
	}

	/**
	 * 批量添加商品
	 * @param product
	 * @param productImgHolderList
	 */
	private void addProductImgList(Product product, List<ImageHolder> productImgHolderList) {
		// 获取图片存储路径，这里直接存放到相应店铺的文件夹底下
		String dest = PathUtil.getShopImgPath(product.getShop().getShopId());
		List<ProductImg> productImgList = new ArrayList<ProductImg>();
		// 遍历图片一次去处理，并添加进productImg实体类里
		for (ImageHolder productImgHolder : productImgHolderList) {
			String imgAddr = ImageUtil.generateNormalImg(productImgHolder, dest);
			ProductImg productImg = new ProductImg();
			productImg.setProductImgAddr(imgAddr);
			productImg.setProductId(product.getProductId());
			productImg.setCreateTime(new Date());
			productImgList.add(productImg);
		}
		
		// 如果确实是有图片需要添加的，就执行批量添加操作
		if (productImgList.size() > 0) {
			try {
				int effectedNum = productImgDao.insertProductImgs(productImgList);
				if (effectedNum <= 0) {
					throw new RuntimeException("创建商品详情图片失败");
				}
			} catch (Exception e) {
				throw new RuntimeException("创建商品详情图片失败:" + e.toString());
			}
		}
	}

	/**
	 * 添加商品缩略图
	 * @param product
	 * @param thumbnail
	 */
	private void addThumbnail(Product product, ImageHolder thumbnail) {
		String dest = PathUtil.getShopImgPath(product.getShop().getShopId());
		String thumbnailAddr = ImageUtil.generateThumbnail(thumbnail, dest);
		product.setImgAddr(thumbnailAddr);
	}

	/**
	 * 修改商品信息 及 图片
	 */
	@Override
	@Transactional
	// 1.若缩略图参数有值，则处理缩略图，
	// 若原先存在缩略图则先删除再添加新图，之后获取缩略图相对路径并赋值给product
	// 2.若商品详情图列表参数有值，对商品详情图片列表进行同样的操作
	// 3.将tb_product_img下面的该商品原先的商品详情图记录全部清除
	// 4.更新tb_product_img以及tb_product的信息
	public ProductExecution modifyProduct(Product product, ImageHolder imageHolder,
			List<ImageHolder> productImageHolder) {
		//空值判断
		if (product != null && product.getShop() != null && product.getShop().getShopId() >= 0) {
			// 给商品设置上默认属性
			product.setAlterTime(new Date());
			// 若商品缩略图不为空且原有缩略图不为空则删除原有缩略图并添加
			if (imageHolder != null) {
				// 先获取一遍原有信息，因为原来的信息里有原图片地址
				Product tempProduct = productDao.queryProductById(product.getProductId());
				if (tempProduct.getImgAddr() != null) {
					ImageUtil.deleteFileOrPath(tempProduct.getImgAddr());
				}
				addThumbnail(product, imageHolder);
			}
			// 如果有新存入的商品详情图，则根据productId将原先的删除，并添加新的图片
			if (productImageHolder != null && productImageHolder.size() >= 0) {
				deleteProductImgList(product.getProductId());
				addProductImgList(product, productImageHolder);
			}
			try {
				int effectedNum = productDao.updateProduct(product);
				if (effectedNum <= 0) {
					throw new RuntimeException("更新商品失败");
				}else {
					return new ProductExecution(ProductStateEnum.SUCCESS, product);
				}
			} catch (Exception e) {
				throw new RuntimeException("modifyProduct error:" + e.toString());
			}
		}else {
			return new ProductExecution(ProductStateEnum.EMPTY);
		}
	}

	private void deleteProductImgList(Long productId) {
		// 根据productId获取原来的图片
		List<ProductImg> productImgList = productImgDao.queryProductImg(productId);
		//遍历删除图片
		for (ProductImg productImg : productImgList) {
			ImageUtil.deleteFileOrPath(productImg.getProductImgAddr());
		}
		//删除数据库中的图片
		productImgDao.deleteProductImgByProductId(productId);
	}

	/*
	 * 通过productid获取商品信息
	 */
	@Override
	public Product getProduct(long productId) {
		return productDao.queryProductById(productId);
	}

	/**
	 * 分页查询，根据查询条件获取商品数量 和 列表
	 */
	public ProductExecution getProductList(Product productCondition, int pageIndex, int pageSize) {
		int rowIndex = PageCount.pageIndexToRow(pageIndex, pageSize); //获取从哪开始取数据
		List<Product> productlList = productDao.queryProductList(productCondition, rowIndex, pageSize); //获取店铺列表
		int effectedNum = productDao.queryProductCount(productCondition);
		
		ProductExecution se = new ProductExecution();
		if (productlList != null) {
			se.setProductList(productlList);
			se.setCount(effectedNum);
		}else {
			se.setStateInfo(ProductStateEnum.EMPTY.getStateInfo());
		}
		return se;
	}
}
