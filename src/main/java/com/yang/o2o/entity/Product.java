package com.yang.o2o.entity;

import java.util.Date;
import java.util.List;

//import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * ��Ʒ
 * @author Yang
 *
 */
//@JsonIgnoreProperties({"productCategory"})
public class Product {

	private Long productId;
	private String productName;
	private String productDes;
	// ��Ʒ ͼƬ��ַ
	private String imgAddr;
	// ��Ʒ�������۸�
	private String normalPrice;
	
	// ��Ʒ���ۿۼ�
	private String reducePrice;
	private Integer productWeight;
	// ��Ʒ��״̬��  0���¼ܣ�1��չʾ���˿�
	private Integer productStatus;
	private Date createTime;
	private Date alterTime;
	
	//��Ʒ�����ж��ͼƬ������ø��б�洢��
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
