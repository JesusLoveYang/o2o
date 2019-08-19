package com.yang.o2o.service;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.yang.o2o.BaseTest;
import com.yang.o2o.entity.Area;

public class AreaServiceTest extends BaseTest{
	
	// 一但这个类用到了AreaService接口，那在运行时，spring就会注入它的实现类，因为实现类已经用@Service标注了，
	// 它被springIoC托管着，在用到的时候，就被派到需要的地方去。
	@Autowired
	private AreaService areaService;
	
	@Test
	public void testGetAreaList() {
		List<Area> areaList = areaService.getAreaList();
		assertEquals("西区", areaList.get(0).getAreaName());
	}
}
