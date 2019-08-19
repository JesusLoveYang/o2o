package com.yang.o2o.enums;

public enum ShopStateEnum {
	CHACK(0, "�����"), OFFLINE(-1, "�Ƿ�����"), SUCCESS(1, "�����ɹ�"), 
	PASS(2, "���ͨ��"), INNER_ERROR(-1001, "�ڲ�����"), NULL_SHOP(-1002, "������ϢΪ��");
	
	//״ֵ̬
	private int state;
	//״ֵ̬��˵��
	private String stateInfo;
	
	//˽�л���������ԭ���ǲ�ϣ���������޸��ҵ�enumֵ��
	private ShopStateEnum(int state, String stateInfo) {
		this.state = state;
		this.stateInfo = stateInfo;
	}
	
	//�����ṩһ������ ���ڵ�����״ֵ̬stateʱ��������Ӧ��enum��Ϣ,  ���ǵ���ģʽѽ
	// values() �����ǻ��ö����ʵ������
	public static ShopStateEnum getStateEnum(int state) {
		for (ShopStateEnum stateEnum : values()) {
			if (stateEnum.state == state) {
				return stateEnum;
			}
		}
		
		return null;
	}

	public int getState() {
		return state;
	}

	public String getStateInfo() {
		return stateInfo;
	}
}
