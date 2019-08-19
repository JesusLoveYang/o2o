package com.yang.o2o.dao;

import static org.junit.Assert.assertEquals;

import java.util.Date;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.yang.o2o.BaseTest;
import com.yang.o2o.entity.Area;
import com.yang.o2o.entity.PersonInfo;
import com.yang.o2o.entity.Shop;
import com.yang.o2o.entity.ShopCategory;

public class ShopDaoTest extends BaseTest{

	@Autowired
	private ShopDao shopDao;
	
	@Test
	@Ignore
	public void testQueryShopList() {
		Shop shopCondition = new Shop();
		ShopCategory parentshopCategory = new ShopCategory();
		parentshopCategory.setShopCategoryId(3L);
		
		ShopCategory childshopCategory = new ShopCategory();
		childshopCategory.setParent(parentshopCategory);
		
		shopCondition.setShopCategory(childshopCategory);
		
		List<Shop> shoplList = shopDao.queryShopList(shopCondition, 0, 5);
		int count = shopDao.queryShopCount(shopCondition);
		System.out.println("店铺数量：" + shoplList.size());
		System.out.println(count);
		
	}
	
	@Test
	@Ignore
	public void testQueryById() {
		long shopId = 4l;
		Shop shop = shopDao.queryById(shopId);
		
		System.out.println("店铺的名字：" + shop.getShopName());
		System.out.println("店铺的位置：" + shop.getArea().getAreaName());
		System.out.println("店铺的分类：" + shop.getShopCategory().getShopCategoryName());
	}
	
	@Test
	//@Ignore
	public void testInsertShop() {
		
		Shop shop = new Shop();
		Area area = new Area();
		ShopCategory shopCategory = new ShopCategory();
		PersonInfo personInfo = new PersonInfo();
		
		area.setAreaId(2);
		shopCategory.setShopCategoryId(1l);
		personInfo.setUserId(1l);

		shop.setShopName("测试增加商铺");
		shop.setShopDes("test");
		shop.setShopImg("test");
		shop.setShopAddr("西区");
		shop.setPhone("123");
		shop.setShopWeight(2);
		shop.setStatus(1);
		shop.setShopCreateTime(new Date());
		shop.setAdivce("审核中");
		shop.setArea(area);
		shop.setShopCategory(shopCategory);
		shop.setOwner(personInfo);
		
		int enums = shopDao.insertShop(shop);
		
		assertEquals(1, enums);
	}
	
	@Test
	@Ignore
	public void testUpdateShop() {
		
		Shop shop = new Shop();
		
		shop.setShopId(2L);
		shop.setShopImg("测试图片");
		shop.setShopAlterTime(new Date());
		
		int nums = shopDao.updateShop(shop);
		assertEquals(1, nums);
	}
}
