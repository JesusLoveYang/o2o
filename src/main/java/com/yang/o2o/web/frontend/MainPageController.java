package com.yang.o2o.web.frontend;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yang.o2o.entity.HeadLine;
import com.yang.o2o.entity.ShopCategory;
import com.yang.o2o.service.HeadLineService;
import com.yang.o2o.service.ShopCategoryService;

@Controller
@RequestMapping("/frontend")
public class MainPageController {

	@Autowired
	private HeadLineService headLineService;
	@Autowired
	private ShopCategoryService shopCategoryService;
	
	@RequestMapping(value = "/listmainpageinfo", method = RequestMethod.GET)
	@ResponseBody
	private Map<String, Object> listMainPageInfo(){
		Map<String, Object> modelMap = new HashMap<String, Object>();
		
		List<ShopCategory> shopCategoryList = new ArrayList<ShopCategory>();
		try {
			//��ȡһ����������б���parent_idΪ�յ� �������
			shopCategoryList = shopCategoryService.getShopCategory(null);
			modelMap.put("shopCategoryList", shopCategoryList);
		} catch (Exception e) {
			modelMap.put("success", false);
			modelMap.put("errMsg", e.toString());
			return modelMap;
		}
		
		List<HeadLine> headLineList = new ArrayList<HeadLine>();
		try {
			//��ȡͷ��״̬Ϊ1��ͷ���б�
			HeadLine headLineCondition = new HeadLine();
			headLineCondition.setHeadLineStatus(1);
			headLineList = headLineService.getHeadLine(headLineCondition);
			modelMap.put("headLineList", headLineList);
		} catch (Exception e) {
			modelMap.put("success", false);
			modelMap.put("errMsg", e.toString());
			return modelMap;
		}
		modelMap.put("success", true);
		return modelMap;
	}
}
