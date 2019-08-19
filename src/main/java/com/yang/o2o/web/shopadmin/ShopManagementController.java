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
 * 实现店铺管理相关的逻辑
 * 
 * @author Yang
 *
 */

@Controller                    //告诉spring这个类是controller层
@RequestMapping("/shopadmin")  //指定前端访问路径
public class ShopManagementController {
	
	@Autowired
	private ShopService shopService;
	
	//** 添加店铺分类信息
	@Autowired
	private ShopCategoryService shopCategoryService;
	
	//** 添加区域列表信息
	@Autowired
	private AreaService areaService;
	
	//**** 店铺管理页面的controller层
	@RequestMapping(value = "/getshopmanagementinfo", method = RequestMethod.GET)
	@ResponseBody
	private Map<String, Object> getShopManagementInfo(HttpServletRequest request){
		Map<String, Object> modelMap = new HashMap<String, Object>();
		
		Long shopId = HttpServletRequestUtil.getLong(request, "shopId");
		if (shopId <= 0) {
			Object currentShopObj = request.getSession().getAttribute("shopId");
			if (currentShopObj == null) {         //游客
				modelMap.put("redirect", true);
				modelMap.put("url", "/o2o/shopadmin/getshoplist");
			}else {        // 从session会话中获取到，判定以前登录过
				Shop currentShop = (Shop)currentShopObj;
				modelMap.put("redirect", false);
				modelMap.put("shopId", currentShop.getShopId());
			}
		}else {   // id大于0，页面输入的有id
			Shop currentShop = new Shop();
			currentShop.setShopId(shopId);
			request.getSession().setAttribute("currentShop", currentShop);
			modelMap.put("redirect", false);
		}
		
		return modelMap;
	}
	
	//**** 获取店铺列表
	@RequestMapping(value = "/getshoplist", method = RequestMethod.GET)
	@ResponseBody
	private Map<String, Object> getShopList(HttpServletRequest request){
		Map<String, Object> modelMap = new HashMap<String, Object>();
		//一般会从用户的登录状态获取店铺列表，这里还么有开发登录，因此我直接设值，模拟登录后，
//		PersonInfo user = new PersonInfo();
//		user.setUserId(1L);
//		user.setUserName("test");
//		request.getSession().setAttribute("user", user);
		
		PersonInfo user =(PersonInfo) request.getSession().getAttribute("user"); //从session会话中 获取用户对象
		
		try {
			Shop shop = new Shop();
			shop.setOwner(user);
			ShopExecution se = shopService.getShopList(shop, 0, 100);
			modelMap.put("shopList", se.getShopList());
			// 列出店铺成功后，将店铺放到session中，用于权限的校验
			request.getSession().setAttribute("shopList", se.getShopList());
			modelMap.put("user", user);
			modelMap.put("success", true);
		} catch (Exception e) {
			modelMap.put("success", false);
			modelMap.put("errMsg", e.getMessage());
		}
		
		return modelMap;
	}
	
	//*** 店铺信息之通过Id获取
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
	
	//*** 店铺信息之修改（与注册店铺很类似）
	@SuppressWarnings("unused")
	@RequestMapping(value = "/modifyshop", method = RequestMethod.POST)  //指定前端访问路径, 接收信息的方式是POST，因为是表单数据
	@ResponseBody                                                          //由于返回值map类型，因此将其转换成json对象
	private Map<String, Object> modifyShop(HttpServletRequest request) {
		
		//定义返回值
		Map<String, Object> modelMap = new HashMap<String, Object>();
		
		//*** 验证码校验
		if (!CodeUtil.checkVerifyCode(request)) {
			modelMap.put("success", false);
			modelMap.put("errMsg", "请输入正确的验证码");
			return modelMap;
		}
		//具体的逻辑：
		//1、接收并转化相应的参数，包括店铺信息和图片信息,  
		//1.1、这个主要实现了从前端接收到了字符串的相关信息，将其转成shop实体类 ， 通过json将字符串转换成shop对象
		String shopStr = HttpServletRequestUtil.getString(request, "shopStr");
		ObjectMapper mapper = new ObjectMapper();   //json
		Shop shop = null;
		
		try {
			shop = mapper.readValue(shopStr, Shop.class); //通过json转成实体类
		} catch (Exception e) {
			modelMap.put("success", false);
			modelMap.put("errMsg", e.getMessage());
			return modelMap;
		} 
		
		//1.2接下来处理图片相关的信息了，CommonsMultipartFile它是spring自带的，  将文件流接收到shopImg里面去。！！
		CommonsMultipartFile shopImg = null;
		//文件上传解析器 去解析request文件的信息, 它从request 本次会话 当中的 上下文获取相关文献上传的内容
		CommonsMultipartResolver commonsMultipartResolver = new CommonsMultipartResolver(
				request.getSession().getServletContext()); 
		// 判断request里面是否有上传的文件流
		if (commonsMultipartResolver.isMultipart(request)) {
			//将request转换成下面那个对象，因此它可以提取相应的文件流
			MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest)request;
			shopImg = (CommonsMultipartFile)multipartHttpServletRequest.getFile("shopImg");
		}
		
		//2、有了店铺shop信息和图片信息， 就可以修改店铺
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
			modelMap.put("errMsg", "请输入店铺id");
			return modelMap;
		}
	}
	
	//**有了service层的引入，我给它个路由，便于前端访问，  method是get 因为我不需要传参数到里面去
	@RequestMapping(value = "/getshopinitinfo", method = RequestMethod.GET)
	@ResponseBody
	private Map<String, Object> getShopInitInfo(){
		
		//定义返回值,主要用于页面展示
		Map<String, Object> modelMap = new HashMap<String, Object>();
		
		//定义商铺分类列表初始值
		List<ShopCategory> shopCategoryList = new ArrayList<ShopCategory>();
		//定义区域列表初始值
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
	
	//参数是：有前端传过来的是request请求
	@RequestMapping(value = "/registershop", method = RequestMethod.POST)  //指定前端访问路径, 接收信息的方式是POST，因为是表单数据
	@ResponseBody                                                          //由于返回值map类型，因此将其转换成json对象
	private Map<String, Object> registerShop(HttpServletRequest request) {
		
		//定义返回值
		Map<String, Object> modelMap = new HashMap<String, Object>();
		
		//** 验证码校验
		if (!CodeUtil.checkVerifyCode(request)) {
			modelMap.put("success", false);
			modelMap.put("errMsg", "请输入正确的验证码");
			return modelMap;
		}
		//具体的逻辑：
		//1、接收并转化相应的参数，包括店铺信息和图片信息,  
		//1.1、这个主要实现了从前端接收到了字符串的相关信息，将其转成shop实体类 ， 通过json将字符串转换成shop对象
		String shopStr = HttpServletRequestUtil.getString(request, "shopStr");
		ObjectMapper mapper = new ObjectMapper();   //json
		Shop shop = null;
		
		try {
			shop = mapper.readValue(shopStr, Shop.class); //通过json转成实体类
		} catch (Exception e) {
			modelMap.put("success", false);
			modelMap.put("errMsg", e.getMessage());
			return modelMap;
		} 
		
		//1.2接下来处理图片相关的信息了，CommonsMultipartFile它是spring自带的，  将文件流接收到shopImg里面去。！！
		CommonsMultipartFile shopImg = null;
		//文件上传解析器 去解析request文件的信息, 它从request 本次会话 当中的 上下文获取相关文献上传的内容
		CommonsMultipartResolver commonsMultipartResolver = new CommonsMultipartResolver(
				request.getSession().getServletContext()); 
		// 判断request里面是否有上传的文件流
		if (commonsMultipartResolver.isMultipart(request)) {
			//将request转换成下面那个对象，因此它可以提取相应的文件流
			MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest)request;
			shopImg = (CommonsMultipartFile)multipartHttpServletRequest.getFile("shopImg");
		}else {
			modelMap.put("success", false);
			modelMap.put("errMsg", "上传图片不能为空");
			return modelMap;
		}
		
		//2、有了店铺shop信息和图片信息， 就可以注册店铺
		if (shop != null && shopImg != null) {
			//假设前端传来的信息是不可靠的， 我越少依赖前端的信息越好
			// 店主的信息可以通过session会话 去获取的，因此不需要前端传过来。 user从哪来呢？主要是用户在操作店铺时是需要登录的，从登录的信息获取
			PersonInfo owner = (PersonInfo)request.getSession().getAttribute("user"); 
			shop.setOwner(owner);
/*************************
			PersonInfo owner = new PersonInfo(); 
			owner.setUserId(1L);   //后面会讲session 这里给出初始化
			shop.setOwner(owner);
			ShopExecution se = shopService.addShop(shop, shopImg); //CommonsMultipartFile与File不能直接相互转换
**************************/	
			ShopExecution se;
			try {
				ImageHolder imageHolder = new ImageHolder(shopImg.getOriginalFilename(),shopImg.getInputStream());
				se = shopService.addShop(shop, imageHolder);
				if (se.getState() == ShopStateEnum.CHACK.getState()) {
					modelMap.put("success", true);
					//该用户可以操作的店铺列表
					@SuppressWarnings("unchecked")
					List<Shop> shopList = (List<Shop>) request.getSession().getAttribute("shopList");
					if (shopList == null || shopList.size() == 0) {
						//第一次登录
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
			modelMap.put("errMsg", "注册店铺失败");
			return modelMap;
		}
		//3、返回结果
	}
}
