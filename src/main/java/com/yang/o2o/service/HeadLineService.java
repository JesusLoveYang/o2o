package com.yang.o2o.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.yang.o2o.entity.HeadLine;

@Service
public interface HeadLineService {

	public static final String HLLISTKEY = "headLineList";
	
	/**
	 * 根据头条条件 获取头条信息
	 * 
	 * @param headLineCondition
	 * @return
	 */
	List<HeadLine> getHeadLine(HeadLine headLineCondition);
}
