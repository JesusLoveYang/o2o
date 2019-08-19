package com.yang.o2o.service;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Date;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.yang.o2o.BaseTest;
import com.yang.o2o.dto.ImageHolder;
import com.yang.o2o.dto.ShopExecution;
import com.yang.o2o.entity.Area;
import com.yang.o2o.entity.PersonInfo;
import com.yang.o2o.entity.Shop;
import com.yang.o2o.entity.ShopCategory;
import com.yang.o2o.enums.ShopStateEnum;

public class ShopServiceTest extends BaseTest{

	@Autowired
	private ShopService shopService;
	
	@Test
	public void testGetShopList() {
		Shop shopCondition = new Shop();
		PersonInfo owner = new PersonInfo();
		owner.setUserId(1L);
		shopCondition.setOwner(owner);
		
		ShopExecution se = shopService.getShopList(shopCondition, 2, 2);
		System.out.println("店铺数量："+se.getShopList().size());
		System.out.println("店铺的总数量：" + se.getShopCount());
	}
	
	@Test
	@Ignore
	public void testModifyShop() throws FileNotFoundException {
		Shop shop = new Shop();
		shop.setShopId(2L);
		shop.setShopName("更改后的店铺名2");
		
		File file = new File("F:\\projectimage\\james.jpg");
		InputStream fileInputStream = new FileInputStream(file);
		
		ImageHolder imageHolder = new ImageHolder(file.getName(), fileInputStream);
		
		ShopExecution se = shopService.modifyShop(shop, imageHolder);
		System.out.println(se.getShop().getShopImg());
	}
	
	@Test
	@Ignore
	public void addShop() throws Exception {
		
		Shop shop = new Shop();
		Area area = new Area();
		ShopCategory shopCategory = new ShopCategory();
		PersonInfo personInfo = new PersonInfo();
		
		area.setAreaId(2);
		shopCategory.setShopCategoryId(1l);
		personInfo.setUserId(1l);

		shop.setShopName("测试增加商铺2");
		shop.setShopDes("test2");
		shop.setShopAddr("西区");
		shop.setPhone("23");
		shop.setShopWeight(1);
		shop.setStatus(ShopStateEnum.CHACK.getState());
		shop.setShopCreateTime(new Date());
		shop.setAdivce("审核中");
		shop.setArea(area);
		shop.setShopCategory(shopCategory);
		shop.setOwner(personInfo);
		
		File file = new File("F:\\projectimage\\james.jpg");
		FileInputStream shopInputStream = new FileInputStream(file); //获取输入流, 不直接获取文件了
		ImageHolder imageHolder = new ImageHolder(file.getName(), shopInputStream);
		
		ShopExecution se = shopService.addShop(shop, imageHolder);
		
		assertEquals(ShopStateEnum.CHACK.getState(), se.getState());
	}
}
