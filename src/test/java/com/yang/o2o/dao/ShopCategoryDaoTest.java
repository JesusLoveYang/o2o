package com.yang.o2o.dao;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.yang.o2o.BaseTest;
import com.yang.o2o.entity.ShopCategory;

public class ShopCategoryDaoTest extends BaseTest{

	@Autowired
	private ShopCategoryDao shopCategoryDao;
	
	@Test
	public void testShopCategoryDao(){
//		List<ShopCategory> shopCategoryList = shopCategoryDao.queryShopCategory(new ShopCategory());
//		assertEquals(2, shopCategoryList.size());
//		
//		ShopCategory shopCategory = new ShopCategory();
//		ShopCategory parentShopCategory = new ShopCategory();
//		
//		parentShopCategory.setShopCategoryId(1l);
//		shopCategory.setParent(parentShopCategory);
		
		List<ShopCategory> shopCategoryList1 = shopCategoryDao.queryShopCategory(null);
		assertEquals(6, shopCategoryList1.size());
		System.out.println(shopCategoryList1.get(0).getShopCategoryName());
		System.out.println(shopCategoryList1.get(0).getShopCategoryDes());
		System.out.println(shopCategoryList1.get(0).getShopCategoryWeight());
		System.out.println(shopCategoryList1.get(0).getShopCategoryImg());
	}
}
