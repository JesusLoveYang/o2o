package com.yang.o2o.entity;

import java.util.Date;

/**
 * 用户信息：用户包括：顾客、店家、超级管理员
 * @author Yang
 *
 */
public class PersonInfo {

	//用户的id
	private Long userId;
	//用户的名字
	private String userName;
	//用户的性别
	private String userSex;
	//用户的照片
	private String userImg;
	//用户的邮箱
	private String userEmail;
	
	//用户的状态：是否有权利登录商城，0：禁止使用商城，1：可以使用
	private Integer status;
	//用户的标记： 1：顾客，2：店家，3：超级管理员
	private Integer userMark;
	//用户的创建时间
	private Date userCreateTime;
	//用户的修改时间
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
