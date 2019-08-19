package com.yang.o2o.dao;

import java.util.List;

import com.yang.o2o.entity.Area;

public interface AreaDao {
	
	/**
	   * 列出区域列表
	 */
	List<Area> queryAreas();
}
