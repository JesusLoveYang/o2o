package com.yang.o2o.entity;

import java.util.Date;

public class LocalAuth {

	// �����˺����������
	//�����˺�id
	private Long localAuthId;
	//�����û���
	private String userName;
	//����
	private String passWord;
	//�����˺Ŵ���ʱ��
	private Date createTime;
	//�����˺��޸�ʱ�䣬��Ϊ��ʱ�����ǻ��޸��˺�����
	private Date alterTime;
	//���û����Խ��й���
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
