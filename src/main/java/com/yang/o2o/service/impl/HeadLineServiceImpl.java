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
	 * ����ķ�������û�м���redis����ʱ�Ĵ��룬
	 * ���濪ʼ����redis����
	 */
	@Transactional
	public List<HeadLine> getHeadLine(HeadLine headLineCondition) {
		//����redis ��key
		String key = HLLISTKEY;
		// ������ն���
		List<HeadLine> headLineList = null;
		// ����jackson����ת��������
		ObjectMapper mapper = new ObjectMapper();
		// ƴ�Ӵ�redis��key������ͷ����ѯ������ͬ����״̬Ϊ0 ����ʾ����Ϊ1 ����ʾ�����ڳ�������ԱӦ��
		if (headLineCondition != null && headLineCondition.getHeadLineStatus() != null) {
			key = key + "_" + headLineCondition.getHeadLineStatus();
		}
		
		// �ж�key�Ƿ����
		if (!jedisKeys.exists(key)) {
			// �����ݿ��л�ȡ
			headLineList = headLineDao.queryHeadLine(headLineCondition);
			// �����ݸ��Ƹ�redisһ�ݣ����ڲ�ѯ��������ʵ���б����ͨ��json����ת���ַ�����ʽ
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
			// �����ڣ����redis�л�ȡ
			String jString = jediStrings.get(key);
			// ָ��Ҫ��stringת���ɵļ�������
			JavaType javaType = mapper.getTypeFactory().constructParametricType(ArrayList.class, HeadLine.class);
			// ����ص�key��Ӧ��value���Stringת���ɶ����ʵ���༯��
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
