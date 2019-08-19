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

	// �Գ�Ա�����ķ�ʽ ע�����
	@Autowired
	private ShopDao shopDao;
	
	@Transactional
	public ShopExecution addShop(Shop shop, ImageHolder thumbnail) {
		//�����жϵ����Ƿ�Ϊ��;Ϊ��ʱ������ShopExecution�������ù��캯�� ����ö���������ʵ����
		if (shop == null) {
			return new ShopExecution(ShopStateEnum.NULL_SHOP);
		}
		
		try {
			//���е������ʱ���ȳ�ʼ�����̵�״̬������ʱ�䡢�޸�ʱ��
			shop.setStatus(0);
			shop.setShopCreateTime(new Date());
			shop.setShopAlterTime(new Date());
			
			//���ӵ�����Ϣ
			int effectNum = shopDao.insertShop(shop);
			//���ж�һ����һ���Ĳ����Ƿ���Ч, ������Ҫ�׳� RuntimeException ������Ҫ�����ǣ�һ�������� �Ὣ��ǰ�Ĳ���ȡ�����ع���ԭ���ĳ�ʼ״̬��
			if (effectNum <= 0) {
				throw new RuntimeException("���̴���ʧ��");
			}else {
				//��ʱ���̲���ɹ������ͼƬ�ĵ�ַ
				if (thumbnail.getImage() != null) {
					//�洢ͼƬ, ��һ������ ��shopʵ�������ΪҪ�õ�����id ����ͼƬ��Ŀ¼���ڶ������� ��shopImg�ļ��� �洢����ص�Ŀ¼���档
					// ���ܻ���� ��try��Χ
					try {
						addShopImg(shop, thumbnail);
					} catch (Exception e) {
						throw new RuntimeException("addShopImg error" + e.getMessage());
					}
				}
				// ���µ���ͼƬ�ĵ�ַ
				effectNum = shopDao.updateShop(shop);
				if (effectNum <= 0) {
					throw new RuntimeException("���̸���ʧ��");
				}
			}
		} catch (Exception e) {
			throw new RuntimeException("���ӵ���ʧ�ܣ�" + e.getMessage());
		}
		
		//�����ɹ�ʱ������ShopExecution����
		return new ShopExecution(ShopStateEnum.CHACK, shop);
	}

	private void addShopImg(Shop shop, ImageHolder thumbnail) {
		// ��ȡshopͼƬĿ¼�����ֵ·��
		String dest = PathUtil.getShopImgPath(shop.getShopId());
		//Ȼ��洢ͼƬ�����������ֵ·��
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
			//���жϵ����Ƿ�Ϊ��
			if (shop == null || shop.getShopId() == null) {
				return new ShopExecution(ShopStateEnum.NULL_SHOP);
			}else {
				//��Ҫ������
				//��һ�����ж��Ƿ���Ҫ����ͼƬ,�������Ҫ����ͼƬ���Ǿ���Ҫ��ԭ����ͼƬɾ�������ڽ��и�������Ҫ��ɾ����������в���
				if (thumbnail.getImage() != null && thumbnail.getImageName() != null && !"".equals(thumbnail.getImageName())) {
					Shop tempShop = shopDao.queryById(shop.getShopId());
					if (tempShop.getShopImg() != null) {
						ImageUtil.deleteFileOrPath(tempShop.getShopImg());
					}
					addShopImg(shop, thumbnail);
				}
				//�ڶ����������Ҫ ����ͼƬ  ���µ���
				shop.setShopAlterTime(new Date());  //�޸�һ�¸���ʱ��
				int effectNum = shopDao.updateShop(shop);
				
				if (effectNum <= 0) {
					return new ShopExecution(ShopStateEnum.INNER_ERROR);
				}else {
					shop = shopDao.queryById(shop.getShopId());
					return new ShopExecution(ShopStateEnum.SUCCESS, shop);
				}
			}
		} catch (Exception e) {
			throw new RuntimeException("���µ���ʧ�ܣ�" + e.getMessage());
		}
	}

	@Override
	public ShopExecution getShopList(Shop shopCondition, int pageIndex, int pageSize) {
		int rowIndex = PageCount.pageIndexToRow(pageIndex, pageSize); //��ȡ���Ŀ�ʼȡ����
		List<Shop> shoplList = shopDao.queryShopList(shopCondition, rowIndex, pageSize); //��ȡ�����б�
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
