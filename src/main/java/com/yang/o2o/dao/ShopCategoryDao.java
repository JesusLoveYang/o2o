package com.yang.o2o.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yang.o2o.entity.ShopCategory;

public interface ShopCategoryDao {

	//���ݴ���Ĳ���    ��ȡ���̵����
	List<ShopCategory> queryShopCategory(@Param("shopCategoryCondition") ShopCategory shopCategory);
}
