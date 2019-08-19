package com.yang.o2o.entity;

import java.util.Date;

public class HeadLine {

	//头条的id
	private Long headLineId;
	//头条的名字
	private String headLineName;
	//头条的权重
	private Integer headLineWeight;
	//头条的状态，是否有权利展示 0：不可用，1 ：可用
	private Integer headLineStatus;
	//头条的链接，点开某个 图片 可以进入相应的地方
	private String headLineLink;
	//头条的展示图片
	private String headLineImg;
	//头条的创建时间
	private Date createTime;
	//头条的修改时间
	private Date alterTime;
	
	public Long getHeadLineId() {
		return headLineId;
	}
	public void setHeadLineId(Long headLineId) {
		this.headLineId = headLineId;
	}
	public String getHeadLineName() {
		return headLineName;
	}
	public void setHeadLineName(String headLineName) {
		this.headLineName = headLineName;
	}
	public Integer getHeadLineWeight() {
		return headLineWeight;
	}
	public void setHeadLineWeight(Integer headLineWeight) {
		this.headLineWeight = headLineWeight;
	}
	public Integer getHeadLineStatus() {
		return headLineStatus;
	}
	public void setHeadLineStatus(Integer headLineStatus) {
		this.headLineStatus = headLineStatus;
	}
	public String getHeadLineLink() {
		return headLineLink;
	}
	public void setHeadLineLink(String headLineLink) {
		this.headLineLink = headLineLink;
	}
	public String getHeadLineImg() {
		return headLineImg;
	}
	public void setHeadLineImg(String headLineImg) {
		this.headLineImg = headLineImg;
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
	
	
}
