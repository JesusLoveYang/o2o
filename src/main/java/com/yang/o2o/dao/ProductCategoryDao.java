package com.yang.o2o.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yang.o2o.entity.ProductCategory;

public interface ProductCategoryDao {

	/**
	 * ɾ����Ʒ���
	 * 
	 * @param productCategoryId
	 * @param shopId
	 * @return
	 */
	int deleteProductCategory(@Param("productCategoryId") long productCategoryId, @Param("shopId") long shopId);

	/**
	 * �����Ʒ���
	 * 
	 * @param productCategoryList
	 * @return
	 */
	int addProductCategorys(List<ProductCategory> productCategoryList);

	/**
	 * ���ݴ���ĵ���id ��������ȡ��Ʒ����б�
	 * 
	 * @param productCategoryCondition
	 * @return
	 */
	List<ProductCategory> queryProductCategory(long shopId);
}
