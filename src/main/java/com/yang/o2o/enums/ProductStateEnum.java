package com.yang.o2o.enums;

public enum ProductStateEnum {
	CHACK(0, "审核中"), OFFLINE(-1, "非法操作"), SUCCESS(1, "操作成功"), 
	PASS(2, "审核通过"), INNER_ERROR(-1001, "内部错误"), EMPTY(-1002, "商品为空");
	
	//状态值
	private int state;
	//状态值的说明
	private String stateInfo;
	
	//私有化构造器，原因是不希望第三方修改我的enum值，
	private ProductStateEnum(int state, String stateInfo) {
		this.state = state;
		this.stateInfo = stateInfo;
	}
	
	//对外提供一个方法 便于当传入状态值state时，返回相应的enum信息,  这是单利模式呀
	// values() 方法是获得枚举类实例数组
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
