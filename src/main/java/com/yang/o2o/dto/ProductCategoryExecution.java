package com.yang.o2o.dto;

import java.util.List;

import com.yang.o2o.entity.ProductCategory;
import com.yang.o2o.enums.ProductCategoryStateEnum;

public class ProductCategoryExecution {

	/**
	 * ������Ҫ�Ǽ�¼����ֵ���ͣ�����¼һЩ״̬
	 * @author Yang
	 *
	 */
	// ���״̬����������֣�  Ϊ��д��ö����
	private int state;
	
	//״̬��ʾ����������ڽ������ֵĺ��� ��Ϊ��д��ö����
	private String stateInfo;
	
	//��ѯ��ʱ�����
	private List<ProductCategory> productCategoryList;
	
	public ProductCategoryExecution() {
		
	}
	
	//����ʧ�ܵ�ʱ�� ʹ�õ� ������
	public ProductCategoryExecution(ProductCategoryStateEnum stateEnum) {
		this.state = stateEnum.getState(); //״̬
		this.stateInfo = stateEnum.getStateInfo(); //״̬��Ϣ
	}
	
	//�����ɹ���ʱ�� ʹ�õĹ�����
	public ProductCategoryExecution(ProductCategoryStateEnum stateEnum, List<ProductCategory> productCategoryList) {
		this.state = stateEnum.getState(); //״̬
		this.stateInfo = stateEnum.getStateInfo(); //״̬��Ϣ
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
