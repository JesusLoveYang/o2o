package com.yang.o2o.enums;

public enum ProductStateEnum {
	CHACK(0, "�����"), OFFLINE(-1, "�Ƿ�����"), SUCCESS(1, "�����ɹ�"), 
	PASS(2, "���ͨ��"), INNER_ERROR(-1001, "�ڲ�����"), EMPTY(-1002, "��ƷΪ��");
	
	//״ֵ̬
	private int state;
	//״ֵ̬��˵��
	private String stateInfo;
	
	//˽�л���������ԭ���ǲ�ϣ���������޸��ҵ�enumֵ��
	private ProductStateEnum(int state, String stateInfo) {
		this.state = state;
		this.stateInfo = stateInfo;
	}
	
	//�����ṩһ������ ���ڵ�����״ֵ̬stateʱ��������Ӧ��enum��Ϣ,  ���ǵ���ģʽѽ
	// values() �����ǻ��ö����ʵ������
	public static ProductStateEnum getStateEnum(int state) {
		for (ProductStateEnum stateEnum : values()) {
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
