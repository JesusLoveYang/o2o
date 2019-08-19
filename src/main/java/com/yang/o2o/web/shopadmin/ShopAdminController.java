package com.yang.o2o.web.shopadmin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 由该类控制转发 html页面
 * 
 * @author Yang
 *
 */

@Controller
@RequestMapping("/shopadmin") //或者：@RequestMapping(value = "/shopadmin", method = {RequestMethod.GET})
public class ShopAdminController {

	@RequestMapping(value = "/shopoperation", method = RequestMethod.GET) //或者：@RequestMapping(value = "/shopoperation")
	public String shopOperation() {
		return "shop/shopoperation";
	}
	
	@RequestMapping(value = "/shoplist")
	public String shopList() {
		return "shop/shoplist";
	}
	
	@RequestMapping(value = "/shopmanagement")
	public String shopManagement() {
		return "shop/shopmanagement";
	}
	
	@RequestMapping(value = "/productcategorymanagement", method = RequestMethod.GET)
	public String productCategoryManagement() {
		return "shop/productcategorymanagement";
	}
	
	@RequestMapping(value = "/productoperation", method = RequestMethod.GET)
	public String productOperation() {
		return "shop/productoperation";
	}
	
	@RequestMapping(value = "/productmanagement", method = RequestMethod.GET)
	public String productManagement() {
		return "shop/productmanagement";
	}
}
