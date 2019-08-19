package com.yang.o2o.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.yang.o2o.dto.ProductCategoryExecution;
import com.yang.o2o.entity.ProductCategory;

@Service
public interface ProductCategoryService {

	/**
	 * 删除店铺列表
	 * 
	 * @param productCategoryId
	 * @param shopId
	 * @return
	 */
	ProductCategoryExecution deleteProductCategory(long productCategoryId, long shopId);

	/**
	 * 批量添加店铺商品类别信息
	 * 
	 * @param productCategories
	 * @return
	 */
	ProductCategoryExecution batchAddProductCategory(List<ProductCategory> productCategories);

	/**
	 * 通过店铺id 获取该店铺商品类别
	 * 
	 * @param shopId
	 * @return
	 */
	List<ProductCategory> getProductCategoryList(long shopId);
	
}
