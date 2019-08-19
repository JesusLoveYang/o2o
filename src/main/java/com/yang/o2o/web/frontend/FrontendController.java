package com.yang.o2o.web.frontend;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/frontend")
public class FrontendController {

	@RequestMapping(value = "/index")
	private String getIndex() {
		return "frontend/index";
	}
	
	@RequestMapping(value = "/shoplist")
	private String getShopList() {
		return "frontend/shoplist";
	}
	
	/**
	 * ��������ҳ·��
	 * @return
	 */
	@RequestMapping(value = "/shopdetail", method = RequestMethod.GET)
	private String getShopDetail() {
		return "frontend/shopdetail";
	}
	
	/**
	 * ��Ʒ����ҳ·��
	 */
	@RequestMapping(value = "/productdetail", method = RequestMethod.GET)
	private String getProductDetail() {
		return "frontend/productdetail";
	}
}
