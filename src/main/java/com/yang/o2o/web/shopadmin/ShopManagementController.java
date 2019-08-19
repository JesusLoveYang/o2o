package com.yang.o2o.web.shopadmin;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yang.o2o.dto.ImageHolder;
import com.yang.o2o.dto.ShopExecution;
import com.yang.o2o.entity.Area;
import com.yang.o2o.entity.PersonInfo;
import com.yang.o2o.entity.Shop;
import com.yang.o2o.entity.ShopCategory;
import com.yang.o2o.enums.ShopStateEnum;
import com.yang.o2o.service.AreaService;
import com.yang.o2o.service.ShopCategoryService;
import com.yang.o2o.service.ShopService;
import com.yang.o2o.util.CodeUtil;
import com.yang.o2o.util.HttpServletRequestUtil;

/**
 * ʵ�ֵ��̹�����ص��߼�
 * 
 * @author Yang
 *
 */

@Controller                    //����spring�������controller��
@RequestMapping("/shopadmin")  //ָ��ǰ�˷���·��
public class ShopManagementController {
	
	@Autowired
	private ShopService shopService;
	
	//** ��ӵ��̷�����Ϣ
	@Autowired
	private ShopCategoryService shopCategoryService;
	
	//** ��������б���Ϣ
	@Autowired
	private AreaService areaService;
	
	//**** ���̹���ҳ���controller��
	@RequestMapping(value = "/getshopmanagementinfo", method = RequestMethod.GET)
	@ResponseBody
	private Map<String, Object> getShopManagementInfo(HttpServletRequest request){
		Map<String, Object> modelMap = new HashMap<String, Object>();
		
		Long shopId = HttpServletRequestUtil.getLong(request, "shopId");
		if (shopId <= 0) {
			Object currentShopObj = request.getSession().getAttribute("shopId");
			if (currentShopObj == null) {         //�ο�
				modelMap.put("redirect", true);
				modelMap.put("url", "/o2o/shopadmin/getshoplist");
			}else {        // ��session�Ự�л�ȡ�����ж���ǰ��¼��
				Shop currentShop = (Shop)currentShopObj;
				modelMap.put("redirect", false);
				modelMap.put("shopId", currentShop.getShopId());
			}
		}else {   // id����0��ҳ���������id
			Shop currentShop = new Shop();
			currentShop.setShopId(shopId);
			request.getSession().setAttribute("currentShop", currentShop);
			modelMap.put("redirect", false);
		}
		
		return modelMap;
	}
	
	//**** ��ȡ�����б�
	@RequestMapping(value = "/getshoplist", method = RequestMethod.GET)
	@ResponseBody
	private Map<String, Object> getShopList(HttpServletRequest request){
		Map<String, Object> modelMap = new HashMap<String, Object>();
		//һ�����û��ĵ�¼״̬��ȡ�����б����ﻹô�п�����¼�������ֱ����ֵ��ģ���¼��
//		PersonInfo user = new PersonInfo();
//		user.setUserId(1L);
//		user.setUserName("test");
//		request.getSession().setAttribute("user", user);
		
		PersonInfo user =(PersonInfo) request.getSession().getAttribute("user"); //��session�Ự�� ��ȡ�û�����
		
		try {
			Shop shop = new Shop();
			shop.setOwner(user);
			ShopExecution se = shopService.getShopList(shop, 0, 100);
			modelMap.put("shopList", se.getShopList());
			// �г����̳ɹ��󣬽����̷ŵ�session�У�����Ȩ�޵�У��
			request.getSession().setAttribute("shopList", se.getShopList());
			modelMap.put("user", user);
			modelMap.put("success", true);
		} catch (Exception e) {
			modelMap.put("success", false);
			modelMap.put("errMsg", e.getMessage());
		}
		
		return modelMap;
	}
	
	//*** ������Ϣ֮ͨ��Id��ȡ
	@RequestMapping(value = "/getshopbyid", method = RequestMethod.GET)
	@ResponseBody
	private Map<String, Object> getShopById(HttpServletRequest request){
		Map<String, Object> modelMap = new HashMap<String, Object>();
		
		long shopId = HttpServletRequestUtil.getLong(request, "shopId");
		if (shopId >= 0) {
			try {
				Shop shop = shopService.getByShopId(shopId);
				List<Area> areaList = areaService.getAreaList();
				modelMap.put("shop", shop);
				modelMap.put("areaList", areaList);
				modelMap.put("success", true);
			} catch (Exception e) {
				modelMap.put("success", false);
				modelMap.put("errMsg", e.toString());
			}
		}else {
			modelMap.put("success", false);
			modelMap.put("errMsg", "shopId is empty!");
		}
		return modelMap;
	}
	
	//*** ������Ϣ֮�޸ģ���ע����̺����ƣ�
	@SuppressWarnings("unused")
	@RequestMapping(value = "/modifyshop", method = RequestMethod.POST)  //ָ��ǰ�˷���·��, ������Ϣ�ķ�ʽ��POST����Ϊ�Ǳ�����
	@ResponseBody                                                          //���ڷ���ֵmap���ͣ���˽���ת����json����
	private Map<String, Object> modifyShop(HttpServletRequest request) {
		
		//���巵��ֵ
		Map<String, Object> modelMap = new HashMap<String, Object>();
		
		//*** ��֤��У��
		if (!CodeUtil.checkVerifyCode(request)) {
			modelMap.put("success", false);
			modelMap.put("errMsg", "��������ȷ����֤��");
			return modelMap;
		}
		//������߼���
		//1�����ղ�ת����Ӧ�Ĳ���������������Ϣ��ͼƬ��Ϣ,  
		//1.1�������Ҫʵ���˴�ǰ�˽��յ����ַ����������Ϣ������ת��shopʵ���� �� ͨ��json���ַ���ת����shop����
		String shopStr = HttpServletRequestUtil.getString(request, "shopStr");
		ObjectMapper mapper = new ObjectMapper();   //json
		Shop shop = null;
		
		try {
			shop = mapper.readValue(shopStr, Shop.class); //ͨ��jsonת��ʵ����
		} catch (Exception e) {
			modelMap.put("success", false);
			modelMap.put("errMsg", e.getMessage());
			return modelMap;
		} 
		
		//1.2����������ͼƬ��ص���Ϣ�ˣ�CommonsMultipartFile����spring�Դ��ģ�  ���ļ������յ�shopImg����ȥ������
		CommonsMultipartFile shopImg = null;
		//�ļ��ϴ������� ȥ����request�ļ�����Ϣ, ����request ���λỰ ���е� �����Ļ�ȡ��������ϴ�������
		CommonsMultipartResolver commonsMultipartResolver = new CommonsMultipartResolver(
				request.getSession().getServletContext()); 
		// �ж�request�����Ƿ����ϴ����ļ���
		if (commonsMultipartResolver.isMultipart(request)) {
			//��requestת���������Ǹ����������������ȡ��Ӧ���ļ���
			MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest)request;
			shopImg = (CommonsMultipartFile)multipartHttpServletRequest.getFile("shopImg");
		}
		
		//2�����˵���shop��Ϣ��ͼƬ��Ϣ�� �Ϳ����޸ĵ���
		if (shop != null && shop.getShopId() != null) {
			ShopExecution se;
			try {
				ImageHolder imageHolder = new ImageHolder(shopImg.getOriginalFilename(),shopImg.getInputStream());
				
				if (shopImg == null) {
					se = shopService.modifyShop(shop, null);
				}else {
					se = shopService.modifyShop(shop, imageHolder);
				}
				if (se.getState() == ShopStateEnum.SUCCESS.getState()) {
					modelMap.put("success", true);
				}else {
					modelMap.put("success", false);
					modelMap.put("errMsg", se.getStateInfo());
				}
			} catch (IOException e) {
				modelMap.put("success", false);
				modelMap.put("errMsg", e.getMessage());
			}
			return modelMap;
		}else {
			modelMap.put("success", false);
			modelMap.put("errMsg", "���������id");
			return modelMap;
		}
	}
	
	//**����service������룬�Ҹ�����·�ɣ�����ǰ�˷��ʣ�  method��get ��Ϊ�Ҳ���Ҫ������������ȥ
	@RequestMapping(value = "/getshopinitinfo", method = RequestMethod.GET)
	@ResponseBody
	private Map<String, Object> getShopInitInfo(){
		
		//���巵��ֵ,��Ҫ����ҳ��չʾ
		Map<String, Object> modelMap = new HashMap<String, Object>();
		
		//�������̷����б��ʼֵ
		List<ShopCategory> shopCategoryList = new ArrayList<ShopCategory>();
		//���������б��ʼֵ
		List<Area> areaList = new ArrayList<Area>();
		
		try {
			shopCategoryList = shopCategoryService.getShopCategory(new ShopCategory());
			areaList = areaService.getAreaList();
			
			modelMap.put("shopCategoryList", shopCategoryList);
			modelMap.put("areaList", areaList);
			modelMap.put("success", true);
		} catch (Exception e) {
			modelMap.put("success", false);
			modelMap.put("errMsg", e.toString());
		}
		return modelMap;
	}
	
	//�����ǣ���ǰ�˴���������request����
	@RequestMapping(value = "/registershop", method = RequestMethod.POST)  //ָ��ǰ�˷���·��, ������Ϣ�ķ�ʽ��POST����Ϊ�Ǳ�����
	@ResponseBody                                                          //���ڷ���ֵmap���ͣ���˽���ת����json����
	private Map<String, Object> registerShop(HttpServletRequest request) {
		
		//���巵��ֵ
		Map<String, Object> modelMap = new HashMap<String, Object>();
		
		//** ��֤��У��
		if (!CodeUtil.checkVerifyCode(request)) {
			modelMap.put("success", false);
			modelMap.put("errMsg", "��������ȷ����֤��");
			return modelMap;
		}
		//������߼���
		//1�����ղ�ת����Ӧ�Ĳ���������������Ϣ��ͼƬ��Ϣ,  
		//1.1�������Ҫʵ���˴�ǰ�˽��յ����ַ����������Ϣ������ת��shopʵ���� �� ͨ��json���ַ���ת����shop����
		String shopStr = HttpServletRequestUtil.getString(request, "shopStr");
		ObjectMapper mapper = new ObjectMapper();   //json
		Shop shop = null;
		
		try {
			shop = mapper.readValue(shopStr, Shop.class); //ͨ��jsonת��ʵ����
		} catch (Exception e) {
			modelMap.put("success", false);
			modelMap.put("errMsg", e.getMessage());
			return modelMap;
		} 
		
		//1.2����������ͼƬ��ص���Ϣ�ˣ�CommonsMultipartFile����spring�Դ��ģ�  ���ļ������յ�shopImg����ȥ������
		CommonsMultipartFile shopImg = null;
		//�ļ��ϴ������� ȥ����request�ļ�����Ϣ, ����request ���λỰ ���е� �����Ļ�ȡ��������ϴ�������
		CommonsMultipartResolver commonsMultipartResolver = new CommonsMultipartResolver(
				request.getSession().getServletContext()); 
		// �ж�request�����Ƿ����ϴ����ļ���
		if (commonsMultipartResolver.isMultipart(request)) {
			//��requestת���������Ǹ����������������ȡ��Ӧ���ļ���
			MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest)request;
			shopImg = (CommonsMultipartFile)multipartHttpServletRequest.getFile("shopImg");
		}else {
			modelMap.put("success", false);
			modelMap.put("errMsg", "�ϴ�ͼƬ����Ϊ��");
			return modelMap;
		}
		
		//2�����˵���shop��Ϣ��ͼƬ��Ϣ�� �Ϳ���ע�����
		if (shop != null && shopImg != null) {
			//����ǰ�˴�������Ϣ�ǲ��ɿ��ģ� ��Խ������ǰ�˵���ϢԽ��
			// ��������Ϣ����ͨ��session�Ự ȥ��ȡ�ģ���˲���Ҫǰ�˴������� user�������أ���Ҫ���û��ڲ�������ʱ����Ҫ��¼�ģ��ӵ�¼����Ϣ��ȡ
			PersonInfo owner = (PersonInfo)request.getSession().getAttribute("user"); 
			shop.setOwner(owner);
/*************************
			PersonInfo owner = new PersonInfo(); 
			owner.setUserId(1L);   //����ὲsession ���������ʼ��
			shop.setOwner(owner);
			ShopExecution se = shopService.addShop(shop, shopImg); //CommonsMultipartFile��File����ֱ���໥ת��
**************************/	
			ShopExecution se;
			try {
				ImageHolder imageHolder = new ImageHolder(shopImg.getOriginalFilename(),shopImg.getInputStream());
				se = shopService.addShop(shop, imageHolder);
				if (se.getState() == ShopStateEnum.CHACK.getState()) {
					modelMap.put("success", true);
					//���û����Բ����ĵ����б�
					@SuppressWarnings("unchecked")
					List<Shop> shopList = (List<Shop>) request.getSession().getAttribute("shopList");
					if (shopList == null || shopList.size() == 0) {
						//��һ�ε�¼
						shopList = new ArrayList<Shop>();
					}
					shopList.add(se.getShop());
					request.getSession().setAttribute("shopList", shopList);
				}else {
					modelMap.put("success", false);
					modelMap.put("errMsg", se.getStateInfo());
				}
			} catch (IOException e) {
				modelMap.put("success", false);
				modelMap.put("errMsg", e.getMessage());
			}
			return modelMap;
		}else {
			modelMap.put("success", false);
			modelMap.put("errMsg", "ע�����ʧ��");
			return modelMap;
		}
		//3�����ؽ��
	}
}
