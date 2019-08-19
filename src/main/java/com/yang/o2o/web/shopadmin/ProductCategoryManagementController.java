package com.yang.o2o.web.shopadmin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yang.o2o.dto.ProductCategoryExecution;
import com.yang.o2o.entity.ProductCategory;
import com.yang.o2o.entity.Shop;
import com.yang.o2o.enums.ProductCategoryStateEnum;
import com.yang.o2o.service.ProductCategoryService;

@Controller
@RequestMapping("/shopadmin")
public class ProductCategoryManagementController {

	// 获取店铺产品分类
	@Autowired
	private ProductCategoryService productCategoryService;

	/**
	 * 获取店铺产品分类
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/getpclistbyshopid", method = RequestMethod.GET)
	@ResponseBody
	private Map<String, Object> getPCListByShopId(HttpServletRequest request) {
		// 定义返回值类型
		Map<String, Object> modelMap = new HashMap<String, Object>();

		// 定义list 用于存放店铺产品分类列表
		List<ProductCategory> pcList = new ArrayList<ProductCategory>();

		/*
		 * 模拟用户登录 Shop shop = new Shop(); shop.setShopId(2L);
		 * request.getSession().setAttribute("shop", shop);
		 */

		// 从服务器的session中获取; 另外我可以从getshopmanagementinfo 管理店铺信息中可以获得id，因此模拟用户登录
		// 就不需要了，直接获取id即可
		Shop currentShop = (Shop) request.getSession().getAttribute("currentShop");

		if (currentShop != null && currentShop.getShopId() > 0) {
			pcList = productCategoryService.getProductCategoryList(currentShop.getShopId());
			modelMap.put("pcList", pcList);
			modelMap.put("success", true);
		} else {
			modelMap.put("success", false);
			modelMap.put("errMsg", "ProductCategory is empty");
		}

		return modelMap;
	}

	@RequestMapping(value = "/addproductcategorys", method = RequestMethod.POST)
	@ResponseBody
	private Map<String, Object> addProductCategorys(HttpServletRequest request,
			@RequestBody List<ProductCategory> productCategoryList) {   //@RequestBody 将json对象转换成java对象
		Map<String, Object> modelMap = new HashMap<String, Object>();

		Shop shop = (Shop) request.getSession().getAttribute("currentShop");
		
		for (ProductCategory pc : productCategoryList) {
			pc.setShopId(shop.getShopId());
		}
		
		if (productCategoryList != null && productCategoryList.size() > 0) {
			try {
				ProductCategoryExecution pce = productCategoryService.batchAddProductCategory(productCategoryList);
				if (pce.getState() == ProductCategoryStateEnum.SUCCESS.getState()) {
					modelMap.put("success", true);
				}else {
					modelMap.put("success", false);
					modelMap.put("success", pce.getStateInfo());
				}
			} catch (Exception e) {
				modelMap.put("success", false);
				modelMap.put("errMsg", e.getMessage());
				return modelMap;
			}
		}
		
		return modelMap;
	}
	
	@RequestMapping(value = "/removeproductcategory", method = RequestMethod.POST)
	@ResponseBody
	private Map<String, Object> removeproductcategory(HttpServletRequest request, Long productCategoryId){
		Map<String, Object> modelMap = new HashMap<String, Object>();
		
		if (productCategoryId != null && productCategoryId > 0) {
			try {
				Shop shop = (Shop) request.getSession().getAttribute("currentShop");
				ProductCategoryExecution pce = productCategoryService.deleteProductCategory(productCategoryId, shop.getShopId());
				if (pce.getState() == ProductCategoryStateEnum.SUCCESS.getState()) {
					modelMap.put("success", true);
				}else {
					modelMap.put("success", false);
					modelMap.put("success", pce.getStateInfo());
				}
			} catch (Exception e) {
				modelMap.put("success", false);
				modelMap.put("errMsg", e.getMessage());
				return modelMap;
			}
		}else {
			modelMap.put("success", false);
			modelMap.put("errMsg", "请至少添加选择一个商品类别");
		}
		return modelMap;
	}
}
