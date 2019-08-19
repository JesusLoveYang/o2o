package com.yang.o2o.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yang.o2o.cache.JedisUtil;
import com.yang.o2o.dao.HeadLineDao;
import com.yang.o2o.entity.HeadLine;
import com.yang.o2o.service.HeadLineService;

@Service
public class HeadLineServiceImpl implements HeadLineService{

	@Autowired
	private HeadLineDao headLineDao;
	
	@Autowired
	private JedisUtil.Keys jedisKeys;
	@Autowired
	private JedisUtil.Strings jediStrings;
	
	private static Logger logger = LoggerFactory.getLogger(HeadLineServiceImpl.class);
	/*
	 * @Override 
	 * public List<HeadLine> getHeadLine(HeadLine headLineCondition) {
	 *    return headLineDao.queryHeadLine(headLineCondition); 
	 * }
	 */
	
	/*
	 * 上面的方法是在没有加入redis缓存时的代码，
	 * 下面开始引入redis缓存
	 */
	@Transactional
	public List<HeadLine> getHeadLine(HeadLine headLineCondition) {
		//定义redis 的key
		String key = HLLISTKEY;
		// 定义接收对象
		List<HeadLine> headLineList = null;
		// 定义jackson数据转换操作类
		ObjectMapper mapper = new ObjectMapper();
		// 拼接处redis的key，由于头条查询条件不同，若状态为0 则不显示，若为1 则显示，便于超级管理员应用
		if (headLineCondition != null && headLineCondition.getHeadLineStatus() != null) {
			key = key + "_" + headLineCondition.getHeadLineStatus();
		}
		
		// 判断key是否存在
		if (!jedisKeys.exists(key)) {
			// 从数据库中获取
			headLineList = headLineDao.queryHeadLine(headLineCondition);
			// 将数据复制给redis一份，由于查询出来的是实体列表，因此通过json将其转成字符串形式
			String jsonString;
			try {
				jsonString = mapper.writeValueAsString(headLineList);
			} catch (JsonProcessingException e) {
				e.printStackTrace();
				logger.error(e.getMessage());
				throw new RuntimeException(e.getMessage());
			}
			jediStrings.set(key, jsonString);
		}else {
			// 若存在，则从redis中获取
			String jString = jediStrings.get(key);
			// 指定要将string转换成的集合类型
			JavaType javaType = mapper.getTypeFactory().constructParametricType(ArrayList.class, HeadLine.class);
			// 将相关的key对应的value里的String转换成对象的实体类集合
			try {
				headLineList = mapper.readValue(jString, javaType);
			} catch (JsonParseException e) {
				e.printStackTrace();
				logger.error(e.getMessage());
				throw new RuntimeException(e.getMessage());
			} catch (JsonMappingException e) {
				e.printStackTrace();
				logger.error(e.getMessage());
				throw new RuntimeException(e.getMessage());
			} catch (IOException e) {
				e.printStackTrace();
				logger.error(e.getMessage());
				throw new RuntimeException(e.getMessage());
			}
		}
		
		return headLineList;
	}
}
