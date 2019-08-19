package com.yang.o2o.dto;

import java.util.List;

import com.yang.o2o.entity.Product;
import com.yang.o2o.enums.ProductStateEnum;

public class ProductExecution {
	// ���״̬����������֣�  Ϊ��д��ö����
	private int state;
	
	//״̬��ʾ����������ڽ������ֵĺ��� ��Ϊ��д��ö����
	private String stateInfo;
	
	//��Ʒ����
	private int count;
	
	//��Ʒ�Ĳ�������ɾ�ĵ�ʱ����õ���
	private Product product;
	
	//��Ʒ���б���ѯ��ʱ����õ���
	private List<Product> productList;
	
	//���幹�캯��
	public ProductExecution() {
	}
	
	//���εĹ��캯��,�����õ�����ö������,���ö�����;��Ǵ洢�� ״ֵ̬ �ˡ�
	//�������� ������Ʒʧ�ܵ�ʱ�� ������ProductExecution���� ʹ�õĹ�����
	public ProductExecution(ProductStateEnum stateEnum) {
		this.state = stateEnum.getState();  //��ȡ��������
		this.stateInfo = stateEnum.getStateInfo();
	}
	
	//�������� ������Ʒ�ɹ���ʱ�� ������ProductExecution���� ʹ�õĹ�����
	public ProductExecution(ProductStateEnum stateEnum, Product product) {
		this.state = stateEnum.getState();  //��ȡ��������
		this.stateInfo = stateEnum.getStateInfo();
		this.product = product;
	}
	
	//�������� ������Ʒ�ɹ���ʱ�� ������shopexection���� ʹ�õĹ�����
	public ProductExecution(ProductStateEnum stateEnum, List<Product> productList) {
		this.state = stateEnum.getState();  //��ȡ��������
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
