package com.yang.o2o.entity;

import java.util.Date;

/**
 * 区域
 * @author Yang
 *
 */
public class Area {
	//为什么用引用类型，而不用基本数据类型，因为基本数据类型有默认值，比如int默认为0，我们想自己设置值。因此用引用数据类型，初始值为null
	//区域的id，代表有多少个区域
	private Integer areaId;
	//区域的名字
	private String areaName;
	//区域的权重
	private Integer areaWeight;
	//区域的创建时间
	private Date areaCreateTime;
	//区域的修改时间
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
