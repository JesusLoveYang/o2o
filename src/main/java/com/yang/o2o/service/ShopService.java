package com.yang.o2o.service;

import com.yang.o2o.dto.ImageHolder;
import com.yang.o2o.dto.ShopExecution;
import com.yang.o2o.entity.Shop;

public interface ShopService {
	
	/**
	 *      根据pageIndex pageSize分页获取店铺列表
	 * @param shopCondition
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 */
	ShopExecution getShopList(Shop shopCondition, int pageIndex, int pageSize);
	
	/**
	 * 通过shopId 查询店铺信息
	 * 
	 * @param shopId
	 * @return
	 */
	Shop getByShopId(long shopId);
	
	/**
	 * 更新店铺，包括对图片的处理
	 * 
	 * @param shop
	 * @param shopImgInputStream
	 * @param fileName
	 * @return
	 */
	ShopExecution modifyShop(Shop shop, ImageHolder thumbnail);
	
	/**
	 *  添加店铺 和 店 铺图片
	 * 
	 * @param shop
	 * @param shopImgInputStream
	 * @param fileName
	 * @return
	 */
	ShopExecution addShop(Shop shop, ImageHolder thumbnail);
}
