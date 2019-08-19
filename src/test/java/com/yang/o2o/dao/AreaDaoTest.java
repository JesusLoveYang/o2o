package com.yang.o2o.dao;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.yang.o2o.BaseTest;
import com.yang.o2o.entity.Area;

// AreaDaoTest ÿ��������ʱ�� ����BaseTest�ķ���������spring-dao�������ļ�
public class AreaDaoTest extends BaseTest{
	
	// ������spring��ע�⿪��������Ҫ��ͨ��new�ֶ�����AreaDao�����ˣ�����ͨ��ע�ⷽʽ
	@Autowired
	private AreaDao areaDao;
	
	@Test
	public void testQueryArea() {
		List<Area> arealist = areaDao.queryAreas();
		assertEquals(2, arealist.size());
	}
}
