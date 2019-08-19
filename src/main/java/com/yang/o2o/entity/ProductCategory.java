package com.yang.o2o.entity;

import java.util.Date;

public class ProductCategory {

	//商品类别的id
	private Long productCategoryId;
	//商品类别的 所关联的店铺id
	private Long shopId;
	//商品类别的名字
	private String productCategoryName;
	//商品类别的权重
	private Integer weight;
	//商品类别的创建时间
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
