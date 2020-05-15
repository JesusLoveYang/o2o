package com.yang.o2o.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yang.o2o.entity.HeadLine;

public interface HeadLineDao {

	/**
	 * ����ͷ�������� ����ȡͷ�����б�
	 * 
	 * @param headLineCondition
	 * @return
	 */
	List<HeadLine> queryHeadLine(@Param("headLineCondition") HeadLine headLineCondition);
}