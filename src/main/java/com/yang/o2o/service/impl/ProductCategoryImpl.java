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
					throw new RuntimeException("添加商品类别异常");
				}
			} catch (Exception e) {
				throw new RuntimeException("添加商品类型异常：" + e.getMessage());
			}
		} else {
			return new ProductCategoryExecution(ProductCategoryStateEnum.EMPTY_LIST);
		}
	}

	@Override
	@Transactional
	public ProductCategoryExecution deleteProductCategory(long productCategoryId, long shopId) {
		//删除商品类别分两步
		// 第一：先将tb_product中的productCategory置为空，取消与productCategory的关联
		try {
			int effectedNum = productDao.updateProductCategoryToNull(productCategoryId);
			if (effectedNum < 0) {
				throw new RuntimeException("商品类别更新失败");
			}
		} catch (Exception e1) {
			throw new RuntimeException("deleteProductCategory error:" + e1.toString());
		}
		// 第二：删除productCategory
		try {
			int effectNum = productCategoryDao.deleteProductCategory(productCategoryId, shopId);
			if (effectNum <= 0) {
				throw new RuntimeException("店铺类别删除失败！");
			} else {
				return new ProductCategoryExecution(ProductCategoryStateEnum.SUCCESS);
			}
		} catch (Exception e) {
			throw new RuntimeException("deleteProductCategory error" + e.getMessage());
		}
	}
}
