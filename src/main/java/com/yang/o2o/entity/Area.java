package com.yang.o2o.entity;

import java.util.Date;

/**
 * ����
 * @author Yang
 *
 */
public class Area {
	//Ϊʲô���������ͣ������û����������ͣ���Ϊ��������������Ĭ��ֵ������intĬ��Ϊ0���������Լ�����ֵ������������������ͣ���ʼֵΪnull
	//�����id�������ж��ٸ�����
	private Integer areaId;
	//���������
	private String areaName;
	//�����Ȩ��
	private Integer areaWeight;
	//����Ĵ���ʱ��
	private Date areaCreateTime;
	//������޸�ʱ��
	private Date areaAlterTime;

	public Integer getAreaId() {
		return areaId;
	}

	public void setAreaId(Integer areaId) {
		this.areaId = areaId;
	}

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	public Integer getAreaWeight() {
		return areaWeight;
	}

	public void setAreaWeight(Integer areaWeight) {
		this.areaWeight = areaWeight;
	}

	public Date getAreaCreateTime() {
		return areaCreateTime;
	}

	public void setAreaCreateTime(Date areaCreateTime) {
		this.areaCreateTime = areaCreateTime;
	}

	public Date getAreaAlterTime() {
		return areaAlterTime;
	}

	public void setAreaAlterTime(Date areaAlterTime) {
		this.areaAlterTime = areaAlterTime;
	}

}
