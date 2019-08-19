package com.yang.o2o.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yang.o2o.cache.JedisUtil;
import com.yang.o2o.dao.ShopCategoryDao;
import com.yang.o2o.entity.ShopCategory;
import com.yang.o2o.service.ShopCategoryService;

@Service
public class ShopCategoryServiceImpl implements ShopCategoryService{
	
	@Autowired
	private ShopCategoryDao shopCategoryDao;
	@Autowired
	private JedisUtil.Keys jedisKeys;
	@Autowired
	private JedisUtil.Strings jediStrings;
	
	private static Logger logger = LoggerFactory.getLogger(ShopCategoryServiceImpl.class);
	
	/*
	 * public List<ShopCategory> getShopCategory(ShopCategory shopCategoryCondition){ 
	 *    return shopCategoryDao.queryShopCategory(shopCategoryCondition); 
	 * }
	 */
	
	/*
	 * ����ķ�����û�м���redis�����
	 * ���濪ʼ����redis����
	 */
	public List<ShopCategory> getShopCategory(ShopCategory shopCategoryCondition) {
		// ����redis��key
		String key = SCLISTKEY;
		// ������ն���
		List<ShopCategory> shopCategoryList = null;
		// ����jackson����ת��������
		ObjectMapper mapper = new ObjectMapper();
		// ƴ�ӳ�redis��key
		if (shopCategoryCondition == null) {
			// ����ѯ����Ϊ�գ����г�������ҳ���࣬��parentIdΪ�յĵ������
			key = key + "_allfirstlevel";
		}else if (shopCategoryCondition != null && shopCategoryCondition.getParent() != null
				&& shopCategoryCondition.getParent().getShopCategoryId() != null) {
			// ��parentIdΪ�ǿգ����г���parentId�µ����������
			key = key + "_parent" + shopCategoryCondition.getParent().getShopCategoryId();
		}else if (shopCategoryCondition != null) {
			// �г���������𣬲����������ĸ��࣬���г���
			key = key + "_allsecondlevel";
		}
		
		// �ж�key�Ƿ����
		if (!jedisKeys.exists(key)) {
			// �������ڣ�������ݿ�����ȡ����Ӧ����
			shopCategoryList = shopCategoryDao.queryShopCategory(shopCategoryCondition);
			// ����ص�ʵ���༯��ת����string,����redis�����Ӧ��key��
			String jsonString;
			try {
				jsonString = mapper.writeValueAsString(shopCategoryList);
			} catch (JsonProcessingException e) {
				e.printStackTrace();
				logger.error(e.getMessage());   // AOP˼��
				throw new RuntimeException(e.getMessage());
			}
			jediStrings.set(key, jsonString);
		} else {
			// �����ڣ���ֱ�Ӵ�redis����ȡ����Ӧ����
			String jsonString = jediStrings.get(key);
			// ָ��Ҫ��stringת���ɵļ�������
			JavaType javaType = mapper.getTypeFactory().constructParametricType(ArrayList.class, ShopCategory.class);
			try {
				// �����key��Ӧ��value��ĵ�stringת���ɶ����ʵ���༯��
				shopCategoryList = mapper.readValue(jsonString, javaType);
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
		return shopCategoryList;
	}
}
