package com.yang.o2o.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yang.o2o.entity.Shop;

public interface ShopDao {
	
	/**
	 *  分页查询，可输入的条件：店铺名字（模糊）、状态、店铺类别、区域id、owner_id，  可以组合查询
	 * 
	 * @param shopCondition
	 * @param initIndex  从第几行开始取数据
	 * @param pageSize   取多少条
	 * @return
	 */
	List<Shop> queryShopList(@Param("shopCondition") Shop shopCondition, @Param("initIndex") int initIndex,
			@Param("pageSize") int pageSize);
	
	/**
	 * 获取queryShopList总数
	 * @return
	 */
	int queryShopCount(@Param("shopCondition") Shop shopCondition);
	
	/**
	 *    通过shopId 查询店铺
	 */
	Shop queryById(long shopId);
	
	/**
	 * 新增店铺
	 * @param shop
	 * @return
	 */
	int insertShop(Shop shop);
	
	/**
	 * 更新店铺
	 */
	int updateShop(Shop shop);
}
