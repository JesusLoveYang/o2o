package com.yang.o2o.entity;

import java.util.Date;

/**
 * �û���Ϣ���û��������˿͡���ҡ���������Ա
 * @author Yang
 *
 */
public class PersonInfo {

	//�û���id
	private Long userId;
	//�û�������
	private String userName;
	//�û����Ա�
	private String userSex;
	//�û�����Ƭ
	private String userImg;
	//�û�������
	private String userEmail;
	
	//�û���״̬���Ƿ���Ȩ����¼�̳ǣ�0����ֹʹ���̳ǣ�1������ʹ��
	private Integer status;
	//�û��ı�ǣ� 1���˿ͣ�2����ң�3����������Ա
	private Integer userMark;
	//�û��Ĵ���ʱ��
	private Date userCreateTime;
	//�û����޸�ʱ��
	private Date userAlterTime;
	
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserSex() {
		return userSex;
	}
	public void setUserSex(String userSex) {
		this.userSex = userSex;
	}
	public String getUserImg() {
		return userImg;
	}
	public void setUserImg(String userImg) {
		this.userImg = userImg;
	}
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Integer getUserMark() {
		return userMark;
	}
	public void setUserMark(Integer userMark) {
		this.userMark = userMark;
	}
	public Date getUserCreateTime() {
		return userCreateTime;
	}
	public void setUserCreateTime(Date userCreateTime) {
		this.userCreateTime = userCreateTime;
	}
	public Date getUserAlterTime() {
		return userAlterTime;
	}
	public void setUserAlterTime(Date userAlterTime) {
		this.userAlterTime = userAlterTime;
	}
	
	
}
