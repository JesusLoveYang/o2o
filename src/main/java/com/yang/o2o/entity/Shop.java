package com.yang.o2o.entity;

import java.util.Date;

public class Shop {
	// 店铺id
	private Long shopId;
	// 店铺名字
	private String shopName; 
	// 店铺描述
	private String shopDes; 
	// 店铺地址
	private String shopAddr; 
	// 店铺 的门面照
	private String shopImg; 
	
	// 店铺电话
	private String phone; 
	// 店铺权重
	private Integer shopWeight;
	// 店铺状态， -1：不可用， 0：审核中，1 ：审核合格 可用
	private Integer status;
	// 店铺创建时间
	private Date shopCreateTime;
	// 店铺修改时间
	private Date shopAlterTime;
	
	// 给店铺的建议
	private String adivce;
	// 店铺所属的 区域
	private Area area; 
	// 店铺所属的 类别
	private ShopCategory shopCategory; 
	// 店铺 的拥有者
	private PersonInfo owner;
	
	public Long getShopId() {
		return shopId;
	}
	public void setShopId(Long shopId) {
		this.shopId = shopId;
	}
	public String getShopName() {
		return shopName;
	}
	public void setShopName(String shopName) {
		this.shopName = shopName;
	}
	public String getShopDes() {
		return shopDes;
	}
	public void setShopDes(String shopDes) {
		this.shopDes = shopDes;
	}
	public String getShopAddr() {
		return shopAddr;
	}
	public void setShopAddr(String shopAddr) {
		this.shopAddr = shopAddr;
	}
	public String getShopImg() {
		return shopImg;
	}
	public void setShopImg(String shopImg) {
		this.shopImg = shopImg;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public Integer getShopWeight() {
		return shopWeight;
	}
	public void setShopWeight(Integer shopWeight) {
		this.shopWeight = shopWeight;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Date getShopCreateTime() {
		return shopCreateTime;
	}
	public void setShopCreateTime(Date shopCreateTime) {
		this.shopCreateTime = shopCreateTime;
	}
	public Date getShopAlterTime() {
		return shopAlterTime;
	}
	public void setShopAlterTime(Date shopAlterTime) {
		this.shopAlterTime = shopAlterTime;
	}
	public String getAdivce() {
		return adivce;
	}
	public void setAdivce(String adivce) {
		this.adivce = adivce;
	}
	public Area getArea() {
		return area;
	}
	public void setArea(Area area) {
		this.area = area;
	}
	public ShopCategory getShopCategory() {
		return shopCategory;
	}
	public void setShopCategory(ShopCategory shopCategory) {
		this.shopCategory = shopCategory;
	}
	public PersonInfo getOwner() {
		return owner;
	}
	public void setOwner(PersonInfo owner) {
		this.owner = owner;
	}
	
	
}
