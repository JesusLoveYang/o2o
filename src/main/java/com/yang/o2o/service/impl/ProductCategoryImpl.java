package com.yang.o2o.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yang.o2o.dao.ProductCategoryDao;
import com.yang.o2o.dao.ProductDao;
import com.yang.o2o.dto.ProductCategoryExecution;
import com.yang.o2o.entity.ProductCategory;
import com.yang.o2o.enums.ProductCategoryStateEnum;
import com.yang.o2o.service.ProductCategoryService;

@Service
public class ProductCategoryImpl implements ProductCategoryService {

	@Autowired
	private ProductCategoryDao productCategoryDao;

	@Autowired
	private ProductDao productDao;
	
	@Override
	public List<ProductCategory> getProductCategoryList(long shopId) {
		return productCategoryDao.queryProductCategory(shopId);
	}

	@Override
	@Transactional
	public ProductCategoryExecution batchAddProductCategory(List<ProductCategory> productCategories) {
		if (productCategories != null && productCategories.size() >= 0) {
			try {
				int effectNum = productCategoryDao.addProductCategorys(productCategories);
				if (effectNum > 0) {
					return new ProductCategoryExecution(ProductCategoryStateEnum.SUCCESS);
				} else {
					throw new RuntimeException("�����Ʒ����쳣");
				}
			} catch (Exception e) {
				throw new RuntimeException("�����Ʒ�����쳣��" + e.getMessage());
			}
		} else {
			return new ProductCategoryExecution(ProductCategoryStateEnum.EMPTY_LIST);
		}
	}

	@Override
	@Transactional
	public ProductCategoryExecution deleteProductCategory(long productCategoryId, long shopId) {
		//ɾ����Ʒ��������
		// ��һ���Ƚ�tb_product�е�productCategory��Ϊ�գ�ȡ����productCategory�Ĺ���
		try {
			int effectedNum = productDao.updateProductCategoryToNull(productCategoryId);
			if (effectedNum < 0) {
				throw new RuntimeException("��Ʒ������ʧ��");
			}
		} catch (Exception e1) {
			throw new RuntimeException("deleteProductCategory error:" + e1.toString());
		}
		// �ڶ���ɾ��productCategory
		try {
			int effectNum = productCategoryDao.deleteProductCategory(productCategoryId, shopId);
			if (effectNum <= 0) {
				throw new RuntimeException("�������ɾ��ʧ�ܣ�");
			} else {
				return new ProductCategoryExecution(ProductCategoryStateEnum.SUCCESS);
			}
		} catch (Exception e) {
			throw new RuntimeException("deleteProductCategory error" + e.getMessage());
		}
	}
}
