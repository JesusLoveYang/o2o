package com.yang.o2o.entity;

import java.util.Date;

public class ProductCategory {

	//��Ʒ����id
	private Long productCategoryId;
	//��Ʒ���� �������ĵ���id
	private Long shopId;
	//��Ʒ��������
	private String productCategoryName;
	//��Ʒ����Ȩ��
	private Integer weight;
	//��Ʒ���Ĵ���ʱ��
	private Date createTime;
	public Long getProductCategoryId() {
		return productCategoryId;
	}
	public void setProductCategoryId(Long productCategoryId) {
		this.productCategoryId = productCategoryId;
	}
	public Long getShopId() {
		return shopId;
	}
	public void setShopId(Long shopId) {
		this.shopId = shopId;
	}
	public String getProductCategoryName() {
		return productCategoryName;
	}
	public void setProductCategoryName(String productCategoryName) {
		this.productCategoryName = productCategoryName;
	}
	public Integer getWeight() {
		return weight;
	}
	public void setWeight(Integer weight) {
		this.weight = weight;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
}
