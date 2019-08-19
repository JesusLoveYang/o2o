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

	// ��ȡ���̲�Ʒ����
	@Autowired
	private ProductCategoryService productCategoryService;

	/**
	 * ��ȡ���̲�Ʒ����
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/getpclistbyshopid", method = RequestMethod.GET)
	@ResponseBody
	private Map<String, Object> getPCListByShopId(HttpServletRequest request) {
		// ���巵��ֵ����
		Map<String, Object> modelMap = new HashMap<String, Object>();

		// ����list ���ڴ�ŵ��̲�Ʒ�����б�
		List<ProductCategory> pcList = new ArrayList<ProductCategory>();

		/*
		 * ģ���û���¼ Shop shop = new Shop(); shop.setShopId(2L);
		 * request.getSession().setAttribute("shop", shop);
		 */

		// �ӷ�������session�л�ȡ; �����ҿ��Դ�getshopmanagementinfo ���������Ϣ�п��Ի��id�����ģ���û���¼
		// �Ͳ���Ҫ�ˣ�ֱ�ӻ�ȡid����
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
			@RequestBody List<ProductCategory> productCategoryList) {   //@RequestBody ��json����ת����java����
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
			modelMap.put("errMsg", "���������ѡ��һ����Ʒ���");
		}
		return modelMap;
	}
}
