package com.yang.o2o.entity;

import java.util.Date;

public class LocalAuth {

	// 本地账号有五个属性
	//本地账号id
	private Long localAuthId;
	//本地用户名
	private String userName;
	//密码
	private String passWord;
	//本地账号创建时间
	private Date createTime;
	//本地账号修改时间，因为有时候我们会修改账号密码
	private Date alterTime;
	//与用户属性进行关联
	private PersonInfo personinfo;
	
	public Long getLocalAuthId() {
		return localAuthId;
	}
	public void setLocalAuthId(Long localAuthId) {
		this.localAuthId = localAuthId;
	}
	public String getLocatAuthName() {
		return userName;
	}
	public void setLocatAuthName(String locatAuthName) {
		this.userName = locatAuthName;
	}
	public String getPassWord() {
		return passWord;
	}
	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getAlterTime() {
		return alterTime;
	}
	public void setAlterTime(Date alterTime) {
		this.alterTime = alterTime;
	}
	public PersonInfo getPersoninfo() {
		return personinfo;
	}
	public void setPersoninfo(PersonInfo personinfo) {
		this.personinfo = personinfo;
	}
	
	
}
