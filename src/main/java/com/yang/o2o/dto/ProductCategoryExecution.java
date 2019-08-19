package com.yang.o2o.dto;

import java.util.List;

import com.yang.o2o.entity.ProductCategory;
import com.yang.o2o.enums.ProductCategoryStateEnum;

public class ProductCategoryExecution {

	/**
	 * 该类主要是记录返回值类型，并记录一些状态
	 * @author Yang
	 *
	 */
	// 结果状态，这个是数字，  为他写了枚举类
	private int state;
	
	//状态表示，这个是用于解释数字的含义 ，为他写了枚举类
	private String stateInfo;
	
	//查询的时候会用
	private List<ProductCategory> productCategoryList;
	
	public ProductCategoryExecution() {
		
	}
	
	//操作失败的时候 使用的 构造器
	public ProductCategoryExecution(ProductCategoryStateEnum stateEnum) {
		this.state = stateEnum.getState(); //状态
		this.stateInfo = stateEnum.getStateInfo(); //状态信息
	}
	
	//操作成功的时候 使用的构造器
	public ProductCategoryExecution(ProductCategoryStateEnum stateEnum, List<ProductCategory> productCategoryList) {
		this.state = stateEnum.getState(); //状态
		this.stateInfo = stateEnum.getStateInfo(); //状态信息
		this.productCategoryList = productCategoryList;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public String getStateInfo() {
		return stateInfo;
	}

	public void setStateInfo(String stateInfo) {
		this.stateInfo = stateInfo;
	}

	public List<ProductCategory> getProductCategoryList() {
		return productCategoryList;
	}

	public void setProductCategoryList(List<ProductCategory> productCategoryList) {
		this.productCategoryList = productCategoryList;
	}
	
	
}
