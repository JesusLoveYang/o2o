package com.yang.o2o.entity;

import java.util.Date;

public class WechatAuth {
	//四个属性
	//微信id
	private Long wechatAuthId;
	//openid ：由字母和数字组成
	private String openId;
	//微信创建时间
	private Date createTime;
	//与用户信息关联
	private PersonInfo personInfo;
	
	public Long getWechatAuthId() {
		return wechatAuthId;
	}
	public void setWechatAuthId(Long wechatAuthId) {
		this.wechatAuthId = wechatAuthId;
	}
	public String getOpenId() {
		return openId;
	}
	public void setOpenId(String openId) {
		this.openId = openId;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public PersonInfo getPersonInfo() {
		return personInfo;
	}
	public void setPersonInfo(PersonInfo personInfo) {
		this.personInfo = personInfo;
	}
	
	
}
