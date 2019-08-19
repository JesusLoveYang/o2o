package com.yang.o2o.entity;

import java.util.Date;

/**
 * 详情图片
 * @author Yang
 *
 */
public class ProductImg {

	private Long productImgId;
	// 商品图片地址
	private String productImgAddr;
	// 商品图片描述
	private String productImgDes;
	private Integer productImgWeight;
	// 商品图片属于哪个商品
	private Long productId;
	private Date createTime;
	public Long getProductImgId() {
		return productImgId;
	}
	public void setProductImgId(Long productImgId) {
		this.productImgId = productImgId;
	}
	public String getProductImgAddr() {
		return productImgAddr;
	}
	public void setProductImgAddr(String productImgAddr) {
		this.productImgAddr = productImgAddr;
	}
	public String getProductImgDes() {
		return productImgDes;
	}
	public void setProductImgDes(String productImgDes) {
		this.productImgDes = productImgDes;
	}
	public Integer getProductWeight() {
		return productImgWeight;
	}
	public void setProductWeight(Integer productImgWeight) {
		this.productImgWeight = productImgWeight;
	}
	public Long getProductId() {
		return productId;
	}
	public void setProductId(Long productId) {
		this.productId = productId;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
}
