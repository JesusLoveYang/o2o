package com.yang.o2o.service;

import com.yang.o2o.dto.ImageHolder;
import com.yang.o2o.dto.ShopExecution;
import com.yang.o2o.entity.Shop;

public interface ShopService {
	
	/**
	 *      ����pageIndex pageSize��ҳ��ȡ�����б�
	 * @param shopCondition
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 */
	ShopExecution getShopList(Shop shopCondition, int pageIndex, int pageSize);
	
	/**
	 * ͨ��shopId ��ѯ������Ϣ
	 * 
	 * @param shopId
	 * @return
	 */
	Shop getByShopId(long shopId);
	
	/**
	 * ���µ��̣�������ͼƬ�Ĵ���
	 * 
	 * @param shop
	 * @param shopImgInputStream
	 * @param fileName
	 * @return
	 */
	ShopExecution modifyShop(Shop shop, ImageHolder thumbnail);
	
	/**
	 *  ��ӵ��� �� �� ��ͼƬ
	 * 
	 * @param shop
	 * @param shopImgInputStream
	 * @param fileName
	 * @return
	 */
	ShopExecution addShop(Shop shop, ImageHolder thumbnail);
}
