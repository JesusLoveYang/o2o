package com.yang.o2o.entity;

import java.util.Date;

public class HeadLine {

	//ͷ����id
	private Long headLineId;
	//ͷ��������
	private String headLineName;
	//ͷ����Ȩ��
	private Integer headLineWeight;
	//ͷ����״̬���Ƿ���Ȩ��չʾ 0�������ã�1 ������
	private Integer headLineStatus;
	//ͷ�������ӣ��㿪ĳ�� ͼƬ ���Խ�����Ӧ�ĵط�
	private String headLineLink;
	//ͷ����չʾͼƬ
	private String headLineImg;
	//ͷ���Ĵ���ʱ��
	private Date createTime;
	//ͷ�����޸�ʱ��
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
