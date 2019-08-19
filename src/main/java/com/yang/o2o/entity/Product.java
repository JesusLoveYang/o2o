package com.yang.o2o.entity;

import java.util.Date;
import java.util.List;

//import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * 商品
 * @author Yang
 *
 */
//@JsonIgnoreProperties({"productCategory"})
public class Product {

	private Long productId;
	private String productName;
	private String productDes;
	// 商品 图片地址
	private String imgAddr;
	// 商品的正常价格
	private String normalPrice;
	
	// 商品的折扣价
	private String reducePrice;
	private Integer productWeight;
	// 商品的状态，  0：下架，1：展示给顾客
	private Integer productStatus;
	private Date createTime;
	private Date alterTime;
	
	//商品可以有多个图片，因此用个列表存储，
	private List<ProductImg> productImgList;
	private ProductCategory productCategory;
	private Shop shop;
	public Long getProductId() {
		return productId;
	}
	public void setProductId(Long productId) {
		this.productId = productId;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productNames) {
		this.productName = productNames;
	}
	public String getProductDes() {
		return productDes;
	}
	public void setProductDes(String productDes) {
		this.productDes = productDes;
	}
	public String getImgAddr() {
		return imgAddr;
	}
	public void setImgAddr(String imgAddr) {
		this.imgAddr = imgAddr;
	}
	public String getNormalPrice() {
		return normalPrice;
	}
	public void setNormalPrice(String normalPrice) {
		this.normalPrice = normalPrice;
	}
	public String getReducePrice() {
		return reducePrice;
	}
	public void setReducePrice(String reducePrice) {
		this.reducePrice = reducePrice;
	}
	public Integer getProductWeight() {
		return productWeight;
	}
	public void setProductWeight(Integer productWeight) {
		this.productWeight = productWeight;
	}
	public Integer getProductStatus() {
		return productStatus;
	}
	public void setProductStatus(Integer productStatus) {
		this.productStatus = productStatus;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getAlterTime() {
		return alterTime;
	}
	public void setAlterTime(Date alterTime) {
		this.alterTime = alterTime;
	}
	public List<ProductImg> getProductImgList() {
		return productImgList;
	}
	public void setProductImgList(List<ProductImg> productImgList) {
		this.productImgList = productImgList;
	}
	public ProductCategory getProductCategory() {
		return productCategory;
	}
	public void setProductCategory(ProductCategory productCategory) {
		this.productCategory = productCategory;
	}
	public Shop getShop() {
		return shop;
	}
	public void setShop(Shop shop) {
		this.shop = shop;
	}
	
	
}
