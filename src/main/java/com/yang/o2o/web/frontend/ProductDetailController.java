package com.yang.o2o.web.frontend;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yang.o2o.entity.Product;
import com.yang.o2o.service.ProductService;
import com.yang.o2o.util.HttpServletRequestUtil;

@Controller
@RequestMapping("/frontend")
public class ProductDetailController {
	
	@Autowired
	private ProductService productService;
	
	/**
	 * ������Ʒ��id��ȡ��Ʒ����ϸ��Ϣ
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/listproductdetailpageinfo", method = RequestMethod.GET)
	@ResponseBody
	private Map<String, Object> listProductDetailPageInfo(HttpServletRequest request){
		Map<String, Object> modelMap = new HashMap<String, Object>();
		
		// ��ȡǰ̨���ݹ�����productId
		Long productId = HttpServletRequestUtil.getLong(request, "productId");
		Product product = null;
		if (productId != null) {
			// ����productId��ȡ��Ʒ��Ϣ
			try {
				product = productService.getProduct(productId);
				modelMap.put("product", product);
				modelMap.put("success", true);
			} catch (Exception e) {
				modelMap.put("success", false);
				modelMap.put("errMsg", e.toString());
			}
		}else {
			modelMap.put("success", false);
			modelMap.put("errMsg", "empty productId");
		}
		return modelMap;
	}
}
