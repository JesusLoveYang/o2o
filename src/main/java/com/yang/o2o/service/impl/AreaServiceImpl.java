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
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yang.o2o.cache.JedisUtil;
import com.yang.o2o.dao.AreaDao;
import com.yang.o2o.entity.Area;
import com.yang.o2o.service.AreaService;

// service��������dao��ģ� ������Ҫ��� @Service��ǩ������springIoC������Ҫ�����йܵģ�
@Service
public class AreaServiceImpl implements AreaService{

	//Autowired��ǩ��ϣ��spring�������ڸó������ʱ���Զ���AreaDaoע�뵽���棬
	@Autowired
	private AreaDao areaDao;
	
	@Autowired
	private JedisUtil.Keys jedisKeys;
	@Autowired
	private JedisUtil.Strings jedisStrings;
	
	private static Logger logger = LoggerFactory.getLogger(AreaServiceImpl.class);
	/*
	 * //�÷���������ʱ�������AreaDao��queryAreas�����������ݾݿ���ȡ�������б�
	 * 
	 * @Override 
	 * public List<Area> getAreaList() { 
	 * 		return areaDao.queryAreas(); 
	 * }
	 */
	
	/*
	 * ����ķ���û�м��뻺�漼���Ĵ���
	 * ��ʼ����redis���漼��
	 * redis��Ҫkey ��  value�������Ҫspring�Զ����� key value��ʵ����
	 */
	@Transactional
	public List<Area> getAreaList() {
		// ����redis��key
		String key = AREALIST;
		// ������ն���
		List<Area> areaList = null;
		// ����jackson����ת��������
		ObjectMapper mapper = new ObjectMapper();
		// �ж�key�Ƿ����
		if (!jedisKeys.exists(key)) {
			//�������ڣ�������ݿ�����ȡ����Ӧ������
			areaList = areaDao.queryAreas();
			// ��ѯ�����󣬽���Ӧ��ʵ���༯��ת��string������redis ���� ��Ӧ��key��
			String jsonString;
			try {
				jsonString = mapper.writeValueAsString(areaList);
			} catch (Exception e) {
				e.printStackTrace();
				logger.error(e.getMessage());
				throw new RuntimeException(e.getMessage());
			}
			jedisStrings.set(key, jsonString);
		}else {
			//�����ڣ���ֱ�Ӵ�redis��ȡ����Ӧ������
			String jsonString = jedisStrings.get(key);
			// ָ��Ҫ��stringת���ɵļ�������
			JavaType javaType = mapper.getTypeFactory().constructParametricType(ArrayList.class, Area.class);
			// ����ص�key��Ӧ��value���Stringת���ɶ����ʵ���༯��
			try {
				areaList = mapper.readValue(jsonString, javaType);
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
		return areaList;
	}
}
