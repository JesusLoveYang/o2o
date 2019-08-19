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

//首先给它一个标签，告诉spring容器 它是一个Controller
@Controller
@RequestMapping("/superadmin")  //这个是与url路由相关，就是当我们去调用Controller下面的方法时，必须要在superadmin路径下调用
public class AreaController {

	//在第三部分讲了 logback，在此加入logback
	Logger logger = LoggerFactory.getLogger(AreaController.class);
	
	@Autowired
	private AreaService areaService;
	
	//该方法是 列出 区域列表    ** 还需要加一个路由，意思是需要访问主目录superadmin下的 具体哪个方法 这个地方是 listArea()。前端访问的值  用小写。 
	@RequestMapping(value="/listarea", method = RequestMethod.GET)
	@ResponseBody   //自动的将modelMap对象转换成json对象 返回给前端
	public Map<String, Object> listArea(){
		
		logger.info("=========== start ==========="); //一般在方法的开头打印出start， start是在info里面， 方法的启动和结束 一般用info信息
		
		long startTime = System.currentTimeMillis();  //一般会记录程序的运行时间,主要是用于debug信息的，debug会涉及到调优
		
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
		
		logger.error("testError"); //这个一般写到catch里面，但我的程序不会出错，因此写在这里 看一下效果
		long endTime = System.currentTimeMillis();  
		logger.debug("costTime:[{}ms]", endTime - startTime);
		
		logger.info("=========== end ==========="); 
		
		return modelMap;
	}
}
