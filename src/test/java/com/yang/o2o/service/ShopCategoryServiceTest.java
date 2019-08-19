package com.yang.o2o.service;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.yang.o2o.BaseTest;
import com.yang.o2o.entity.ShopCategory;

public class ShopCategoryServiceTest extends BaseTest{

	@Autowired
	private ShopCategoryService shopCategoryService;
	@Autowired
	private CacheService cacheService;
	
	@Test
	public void testGetShopCategory() {
		List<ShopCategory> categoryList = shopCategoryService.getShopCategory(null);
		System.out.println(categoryList.get(0).getShopCategoryName());
		
		ShopCategory shopCategoryCondition = new ShopCategory();
		ShopCategory parent = new ShopCategory();
		parent.setShopCategoryId(3L);
		shopCategoryCondition.setParent(parent);
		categoryList = shopCategoryService.getShopCategory(shopCategoryCondition);
		System.out.println(categoryList.get(0).getShopCategoryName());
		
	}
}
