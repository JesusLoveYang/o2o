package com.yang.o2o.entity;

import java.util.Date;

/**
 * ����ͼƬ
 * @author Yang
 *
 */
public class ProductImg {

	private Long productImgId;
	// ��ƷͼƬ��ַ
	private String productImgAddr;
	// ��ƷͼƬ����
	private String productImgDes;
	private Integer productImgWeight;
	// ��ƷͼƬ�����ĸ���Ʒ
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
