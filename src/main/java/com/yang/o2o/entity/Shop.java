package com.yang.o2o.entity;

import java.util.Date;

public class Shop {
	// ����id
	private Long shopId;
	// ��������
	private String shopName; 
	// ��������
	private String shopDes; 
	// ���̵�ַ
	private String shopAddr; 
	// ���� ��������
	private String shopImg; 
	
	// ���̵绰
	private String phone; 
	// ����Ȩ��
	private Integer shopWeight;
	// ����״̬�� -1�������ã� 0������У�1 ����˺ϸ� ����
	private Integer status;
	// ���̴���ʱ��
	private Date shopCreateTime;
	// �����޸�ʱ��
	private Date shopAlterTime;
	
	// �����̵Ľ���
	private String adivce;
	// ���������� ����
	private Area area; 
	// ���������� ���
	private ShopCategory shopCategory; 
	// ���� ��ӵ����
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
