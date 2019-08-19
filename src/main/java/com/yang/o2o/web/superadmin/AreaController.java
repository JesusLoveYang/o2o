package com.yang.o2o.web.superadmin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yang.o2o.entity.Area;
import com.yang.o2o.service.AreaService;

//���ȸ���һ����ǩ������spring���� ����һ��Controller
@Controller
@RequestMapping("/superadmin")  //�������url·����أ����ǵ�����ȥ����Controller����ķ���ʱ������Ҫ��superadmin·���µ���
public class AreaController {

	//�ڵ������ֽ��� logback���ڴ˼���logback
	Logger logger = LoggerFactory.getLogger(AreaController.class);
	
	@Autowired
	private AreaService areaService;
	
	//�÷����� �г� �����б�    ** ����Ҫ��һ��·�ɣ���˼����Ҫ������Ŀ¼superadmin�µ� �����ĸ����� ����ط��� listArea()��ǰ�˷��ʵ�ֵ  ��Сд�� 
	@RequestMapping(value="/listarea", method = RequestMethod.GET)
	@ResponseBody   //�Զ��Ľ�modelMap����ת����json���� ���ظ�ǰ��
	public Map<String, Object> listArea(){
		
		logger.info("=========== start ==========="); //һ���ڷ����Ŀ�ͷ��ӡ��start�� start����info���棬 �����������ͽ��� һ����info��Ϣ
		
		long startTime = System.currentTimeMillis();  //һ����¼���������ʱ��,��Ҫ������debug��Ϣ�ģ�debug���漰������
		
		Map<String, Object> modelMap = new HashMap<String, Object>(); 
		List<Area> listAreas = new ArrayList<Area>();
		
		try {
			listAreas = areaService.getAreaList();
			modelMap.put("rows", listAreas);
			modelMap.put("total", listAreas.size());
		} catch (Exception e) {
			e.printStackTrace();
			modelMap.put("success", false);
			modelMap.put("errMsg", e.toString());
		}
		
		logger.error("testError"); //���һ��д��catch���棬���ҵĳ��򲻻�������д������ ��һ��Ч��
		long endTime = System.currentTimeMillis();  
		logger.debug("costTime:[{}ms]", endTime - startTime);
		
		logger.info("=========== end ==========="); 
		
		return modelMap;
	}
}
