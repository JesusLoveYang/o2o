package com.yang.o2o.enums;

public enum ProductCategoryStateEnum {
	SUCCESS(1,"�����ɹ�"), INNER_ERROR(-1001, "����ʧ��"), EMPTY_LIST(-1002, "��������1");
	
	private int state;
	private String stateInfo;
	
	private ProductCategoryStateEnum(int state, String stateInfo) {
		this.state = state;
		this.stateInfo = stateInfo;
	}

	public static ProductCategoryStateEnum stateOfEnum(int index) {
		for (ProductCategoryStateEnum state : values()) {
			if (state.getState() == index) {
				return state;
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