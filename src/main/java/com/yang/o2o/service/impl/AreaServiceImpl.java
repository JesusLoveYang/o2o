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

// service层是依赖dao层的， 另外需要添加 @Service标签，告诉springIoC，是需要你来托管的，
@Service
public class AreaServiceImpl implements AreaService{

	//Autowired标签：希望spring容器，在该程序加载时，自动将AreaDao注入到里面，
	@Autowired
	private AreaDao areaDao;
	
	@Autowired
	private JedisUtil.Keys jedisKeys;
	@Autowired
	private JedisUtil.Strings jedisStrings;
	
	private static Logger logger = LoggerFactory.getLogger(AreaServiceImpl.class);
	/*
	 * //该方法被调用时，会调用AreaDao的queryAreas方法，从数据据库中取出数据列表
	 * 
	 * @Override 
	 * public List<Area> getAreaList() { 
	 * 		return areaDao.queryAreas(); 
	 * }
	 */
	
	/*
	 * 上面的方法没有加入缓存技术的代码
	 * 开始加入redis缓存技术
	 * redis需要key 和  value，因此需要spring自动引入 key value的实现类
	 */
	@Transactional
	public List<Area> getAreaList() {
		// 定义redis的key
		String key = AREALIST;
		// 定义接收对象
		List<Area> areaList = null;
		// 定义jackson数据转换操作类
		ObjectMapper mapper = new ObjectMapper();
		// 判断key是否存在
		if (!jedisKeys.exists(key)) {
			//若不存在，则从数据库里面取出相应的数据
			areaList = areaDao.queryAreas();
			// 查询出来后，将相应的实体类集合转成string，存入redis 里面 对应的key中
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
			//若存在，则直接从redis中取出相应的数据
			String jsonString = jedisStrings.get(key);
			// 指定要将string转换成的集合类型
			JavaType javaType = mapper.getTypeFactory().constructParametricType(ArrayList.class, Area.class);
			// 将相关的key对应的value里的String转换成对象的实体类集合
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
