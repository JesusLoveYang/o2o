package com.yang.o2o.service;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.yang.o2o.BaseTest;
import com.yang.o2o.entity.Area;

public class AreaServiceTest extends BaseTest{
	
	// һ��������õ���AreaService�ӿڣ���������ʱ��spring�ͻ�ע������ʵ���࣬��Ϊʵ�����Ѿ���@Service��ע�ˣ�
	// ����springIoC�й��ţ����õ���ʱ�򣬾ͱ��ɵ���Ҫ�ĵط�ȥ��
	@Autowired
	private AreaService areaService;
	
	@Test
	public void testGetAreaList() {
		List<Area> areaList = areaService.getAreaList();
		assertEquals("����", areaList.get(0).getAreaName());
	}
}
