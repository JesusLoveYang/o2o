package com.yang.o2o.dao;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;

import com.yang.o2o.BaseTest;
import com.yang.o2o.entity.HeadLine;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class HeadLineDaoTest extends BaseTest{

	@Autowired
	private HeadLineDao headLineDao;
	
	@Test
	public void testAHeadLineDao() {
		HeadLine headLineCondition = new HeadLine();
		headLineCondition.setHeadLineStatus(1);
		
		List<HeadLine> list = headLineDao.queryHeadLine(headLineCondition);
		assertEquals(3, list.size());
	}
}
