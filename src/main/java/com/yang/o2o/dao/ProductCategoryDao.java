package com.yang.o2o.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yang.o2o.entity.ProductCategory;

public interface ProductCategoryDao {

	/**
	 * 删除产品类别
	 * 
	 * @param productCategoryId
	 * @param shopId
	 * @return
	 */
	int deleteProductCategory(@Param("productCategoryId") long productCategoryId, @Param("shopId") long shopId);

	/**
	 * 添加商品类别
	 * 
	 * @param productCategoryList
	 * @return
	 */
	int addProductCategorys(List<ProductCategory> productCategoryList);

	/**
	 * 根据传入的店铺id 参数，获取产品类别列表
	 * 
	 * @param productCategoryCondition
	 * @return
	 */
	List<ProductCategory> queryProductCategory(long shopId);
}
