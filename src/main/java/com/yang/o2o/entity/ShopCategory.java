package com.yang.o2o.entity;

import java.util.Date;

public class ShopCategory {
	// ���̵�id
	private Long shopCategoryId;
	// ���̵�����
	private String shopCategoryName;
	// ���̵�����
	private String shopCategoryDes;
	// ���̵�ͼƬ
	private String shopCategoryImg;
	// ���̵�Ȩ��
	private Integer shopCategoryWeight;
	// ���̵Ĵ���ʱ��
	private Date shopCategoryCreateTime;
	// ���̵��޸�ʱ��
	private Date shopCategoryAlterTime;
	// ���̵��ϼ�����
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
