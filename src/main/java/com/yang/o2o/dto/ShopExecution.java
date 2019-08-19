package com.yang.o2o.dto;

import java.util.List;

import com.yang.o2o.entity.Shop;
import com.yang.o2o.enums.ShopStateEnum;

/**
 * ������Ҫ�Ǽ�¼����ֵ���ͣ�����¼һЩ״̬
 * @author Yang
 *
 */
public class ShopExecution {

	// ���״̬����������֣�  Ϊ��д��ö����
	private int state;
	
	//״̬��ʾ����������ڽ������ֵĺ��� ��Ϊ��д��ö����
	private String stateInfo;
	
	//�̵������
	private int shopCount;
	
	//�̵�Shop�Ĳ�������ɾ�ĵ�ʱ����õ���
	private Shop shop;
	
	//�̵���б���ѯ��ʱ����õ���
	private List<Shop> shopList;
	
	//���幹�캯��
	public ShopExecution() {
		
	}
	
	//���εĹ��캯��,�����õ�����ö������,���ö�����;��Ǵ洢�� ״ֵ̬ �ˡ�
	//�������� ��������ʧ�ܵ�ʱ�� ������shopexection���� ʹ�õĹ�����
	public ShopExecution(ShopStateEnum stateEnum) {
		this.state = stateEnum.getState();  //��ȡ��������
		this.stateInfo = stateEnum.getStateInfo();
	}
	
	//�������� �������̳ɹ���ʱ�� ������shopexection���� ʹ�õĹ�����
	public ShopExecution(ShopStateEnum stateEnum, Shop shop) {
		this.state = stateEnum.getState();  //��ȡ��������
		this.stateInfo = stateEnum.getStateInfo();
		this.shop = shop;
	}
	
	//�������� �������̳ɹ���ʱ�� ������shopexection���� ʹ�õĹ�����
	public ShopExecution(ShopStateEnum stateEnum, List<Shop> shopList) {
		this.state = stateEnum.getState();  //��ȡ��������
		this.stateInfo = stateEnum.getStateInfo();
		this.shopList = shopList;
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

	public int getShopCount() {
		return shopCount;
	}

	public void setShopCount(int shopCount) {
		this.shopCount = shopCount;
	}

	public Shop getShop() {
		return shop;
	}

	public void setShop(Shop shop) {
		this.shop = shop;
	}

	public List<Shop> getShopList() {
		return shopList;
	}

	public void setShopList(List<Shop> shopList) {
		this.shopList = shopList;
	}
	
	
}
