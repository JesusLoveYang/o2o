package com.yang.o2o.service;

import java.util.List;

import com.yang.o2o.entity.ShopCategory;

public interface ShopCategoryService {
	
	public static final String SCLISTKEY = "shopCategoryList";
	
	List<ShopCategory> getShopCategory(ShopCategory shopCategoryCondition);
}
