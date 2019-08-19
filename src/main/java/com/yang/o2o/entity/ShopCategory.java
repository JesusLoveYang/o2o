package com.yang.o2o.entity;

import java.util.Date;

public class ShopCategory {
	// 店铺的id
	private Long shopCategoryId;
	// 店铺的名字
	private String shopCategoryName;
	// 店铺的描述
	private String shopCategoryDes;
	// 店铺的图片
	private String shopCategoryImg;
	// 店铺的权重
	private Integer shopCategoryWeight;
	// 店铺的创建时间
	private Date shopCategoryCreateTime;
	// 店铺的修改时间
	private Date shopCategoryAlterTime;
	// 店铺的上级店铺
	private ShopCategory parent;
	
	public Long getShopCategoryId() {
		return shopCategoryId;
	}
	public void setShopCategoryId(Long shopCategoryId) {
		this.shopCategoryId = shopCategoryId;
	}
	public String getShopCategoryName() {
		return shopCategoryName;
	}
	public void setShopCategoryName(String shopCategoryName) {
		this.shopCategoryName = shopCategoryName;
	}
	public String getShopCategoryDes() {
		return shopCategoryDes;
	}
	public void setShopCategoryDes(String shopCategoryDes) {
		this.shopCategoryDes = shopCategoryDes;
	}
	public String getShopCategoryImg() {
		return shopCategoryImg;
	}
	public void setShopCategoryImg(String shopCategoryImg) {
		this.shopCategoryImg = shopCategoryImg;
	}
	public Integer getShopCategoryWeight() {
		return shopCategoryWeight;
	}
	public void setShopCategoryWeight(Integer shopCategoryWeight) {
		this.shopCategoryWeight = shopCategoryWeight;
	}
	public Date getShopCategoryCreateTime() {
		return shopCategoryCreateTime;
	}
	public void setShopCategoryCreateTime(Date shopCategoryCreateTime) {
		this.shopCategoryCreateTime = shopCategoryCreateTime;
	}
	public Date getShopCategoryAlterTime() {
		return shopCategoryAlterTime;
	}
	public void setShopCategoryAlterTime(Date shopCategoryAlterTime) {
		this.shopCategoryAlterTime = shopCategoryAlterTime;
	}
	public ShopCategory getParent() {
		return parent;
	}
	public void setParent(ShopCategory parent) {
		this.parent = parent;
	}
	
}
