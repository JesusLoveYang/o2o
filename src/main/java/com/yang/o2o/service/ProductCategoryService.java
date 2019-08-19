package com.yang.o2o.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.yang.o2o.dto.ProductCategoryExecution;
import com.yang.o2o.entity.ProductCategory;

@Service
public interface ProductCategoryService {

	/**
	 * ɾ�������б�
	 * 
	 * @param productCategoryId
	 * @param shopId
	 * @return
	 */
	ProductCategoryExecution deleteProductCategory(long productCategoryId, long shopId);

	/**
	 * ������ӵ�����Ʒ�����Ϣ
	 * 
	 * @param productCategories
	 * @return
	 */
	ProductCategoryExecution batchAddProductCategory(List<ProductCategory> productCategories);

	/**
	 * ͨ������id ��ȡ�õ�����Ʒ���
	 * 
	 * @param shopId
	 * @return
	 */
	List<ProductCategory> getProductCategoryList(long shopId);
	
}
