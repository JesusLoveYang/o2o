package com.yang.o2o.dto;

import java.util.List;

import com.yang.o2o.entity.Product;
import com.yang.o2o.enums.ProductStateEnum;

public class ProductExecution {
	// 结果状态，这个是数字，  为他写了枚举类
	private int state;
	
	//状态表示，这个是用于解释数字的含义 ，为他写了枚举类
	private String stateInfo;
	
	//商品数量
	private int count;
	
	//商品的操作（增删改的时候会用到）
	private Product product;
	
	//商品的列表（查询的时候会用到）
	private List<Product> productList;
	
	//定义构造函数
	public ProductExecution() {
	}
	
	//带参的构造函数,这里用到的是枚举类型,这个枚举类型就是存储的 状态值 了。
	//这个是针对 操作商品失败的时候 生产的ProductExecution对象 使用的构造器
	public ProductExecution(ProductStateEnum stateEnum) {
		this.state = stateEnum.getState();  //获取整型类型
		this.stateInfo = stateEnum.getStateInfo();
	}
	
	//这个是针对 操作商品成功的时候 生产的ProductExecution对象 使用的构造器
	public ProductExecution(ProductStateEnum stateEnum, Product product) {
		this.state = stateEnum.getState();  //获取整型类型
		this.stateInfo = stateEnum.getStateInfo();
		this.product = product;
	}
	
	//这个是针对 操作商品成功的时候 生产的shopexection对象 使用的构造器
	public ProductExecution(ProductStateEnum stateEnum, List<Product> productList) {
		this.state = stateEnum.getState();  //获取整型类型
		this.stateInfo = stateEnum.getStateInfo();
		this.productList = productList;
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

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public List<Product> getProductList() {
		return productList;
	}

	public void setProductList(List<Product> productList) {
		this.productList = productList;
	}
	
	
}
