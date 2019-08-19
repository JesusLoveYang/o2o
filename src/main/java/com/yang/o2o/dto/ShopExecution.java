package com.yang.o2o.dto;

import java.util.List;

import com.yang.o2o.entity.Shop;
import com.yang.o2o.enums.ShopStateEnum;

/**
 * 该类主要是记录返回值类型，并记录一些状态
 * @author Yang
 *
 */
public class ShopExecution {

	// 结果状态，这个是数字，  为他写了枚举类
	private int state;
	
	//状态表示，这个是用于解释数字的含义 ，为他写了枚举类
	private String stateInfo;
	
	//商店的数量
	private int shopCount;
	
	//商店Shop的操作（增删改的时候会用到）
	private Shop shop;
	
	//商店的列表（查询的时候会用到）
	private List<Shop> shopList;
	
	//定义构造函数
	public ShopExecution() {
		
	}
	
	//带参的构造函数,这里用到的是枚举类型,这个枚举类型就是存储的 状态值 了。
	//这个是针对 操作店铺失败的时候 生产的shopexection对象 使用的构造器
	public ShopExecution(ShopStateEnum stateEnum) {
		this.state = stateEnum.getState();  //获取整型类型
		this.stateInfo = stateEnum.getStateInfo();
	}
	
	//这个是针对 操作店铺成功的时候 生产的shopexection对象 使用的构造器
	public ShopExecution(ShopStateEnum stateEnum, Shop shop) {
		this.state = stateEnum.getState();  //获取整型类型
		this.stateInfo = stateEnum.getStateInfo();
		this.shop = shop;
	}
	
	//这个是针对 操作店铺成功的时候 生产的shopexection对象 使用的构造器
	public ShopExecution(ShopStateEnum stateEnum, List<Shop> shopList) {
		this.state = stateEnum.getState();  //获取整型类型
		this.stateInfo = stateEnum.getStateInfo();
		this.shopList = shopList;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public String getStateInfo() {
		return stateInfo;
	}

	public void setStateInfo(String stateInfo) {
		this.stateInfo = stateInfo;
	}

	public int getShopCount() {
		return shopCount;
	}

	public void setShopCount(int shopCount) {
		this.shopCount = shopCount;
	}

	public Shop getShop() {
		return shop;
	}

	public void setShop(Shop shop) {
		this.shop = shop;
	}

	public List<Shop> getShopList() {
		return shopList;
	}

	public void setShopList(List<Shop> shopList) {
		this.shopList = shopList;
	}
	
	
}
