package com.yang.o2o.dao;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.yang.o2o.BaseTest;
import com.yang.o2o.entity.ProductCategory;

public class ProductCategoryDaoTest extends BaseTest{

	@Autowired
	private ProductCategoryDao productCategoryDao;
	
	@Test
	public void testDeleteProductCategory() {
		long shopId = 2L;
		List<ProductCategory> list = productCategoryDao.queryProductCategory(shopId);
		
		System.out.println(list.size());
		
		for (ProductCategory pc : list) {
			if ("test1".equals(pc.getProductCategoryName()) || "test2".equals(pc.getProductCategoryName())) {
				int effectNum = productCategoryDao.deleteProductCategory(pc.getProductCategoryId(), shopId);
				assertEquals(1, effectNum);
			}
		}
		
		System.out.println(list.size());
	}
	
	@Test
	@Ignore
	public void testAddProductCategory() {

		List<ProductCategory> list = new ArrayList<ProductCategory>();

		ProductCategory pc1 = new ProductCategory();
		pc1.setProductCategoryName("水果类");
		pc1.setShopId(2L);
		pc1.setWeight(12);
		pc1.setCreateTime(new Date());

		ProductCategory pc2 = new ProductCategory();
		pc2.setProductCategoryName("蔬菜类");
		pc2.setShopId(2L);
		pc2.setWeight(30);
		pc2.setCreateTime(new Date());

		list.add(pc1);
		list.add(pc2);

		int effectNum = productCategoryDao.addProductCategorys(list);
		System.out.println("新增商品类别个数：" + effectNum);
	}
	
	@Test
	@Ignore
	public void testQueryProductCategory() {
		
		List<ProductCategory> list = productCategoryDao.queryProductCategory(2L);
		
		System.out.println(list.get(0).getProductCategoryName());
		assertEquals(2, list.size());
	}
}
