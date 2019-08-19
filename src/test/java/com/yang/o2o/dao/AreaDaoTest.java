package com.yang.o2o.dao;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.yang.o2o.BaseTest;
import com.yang.o2o.entity.Area;

// AreaDaoTest 每次启动的时候 加载BaseTest的方法，启动spring-dao的配置文件
public class AreaDaoTest extends BaseTest{
	
	// 我利用spring的注解开发，不需要在通过new手动创建AreaDao对象了，而是通过注解方式
	@Autowired
	private AreaDao areaDao;
	
	@Test
	public void testQueryArea() {
		List<Area> arealist = areaDao.queryAreas();
		assertEquals(2, arealist.size());
	}
}
