package com.yang.o2o.web.local;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yang.o2o.dto.LocalAuthExecution;
import com.yang.o2o.entity.LocalAuth;
import com.yang.o2o.entity.PersonInfo;
import com.yang.o2o.enums.LocalAuthStateEnum;
import com.yang.o2o.service.LocalAuthService;
import com.yang.o2o.util.CodeUtil;
import com.yang.o2o.util.HttpServletRequestUtil;

@Controller
@RequestMapping(value = "/local", method = { RequestMethod.GET, RequestMethod.POST })
public class LocalAuthController {

	@Autowired
	private LocalAuthService localAuthService;

	/**
	 * 将平台账号信息与用户信息绑定
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/bindlocalauth", method = RequestMethod.POST)
	@ResponseBody
	private Map<String, Object> bindLocalAuth(HttpServletRequest request) {
		Map<String, Object> modelMap = new HashMap<String, Object>();

		// 验证码校验
		if (!CodeUtil.checkVerifyCode(request)) {
			modelMap.put("success", false);
			modelMap.put("errMsg", "输入了错误的验证码");
			return modelMap;
		}

		// 获取输入的帐号
		String username = HttpServletRequestUtil.getString(request, "userName");
		// 获取用于的密码
		String password = HttpServletRequestUtil.getString(request, "password");
		// 从session中获取当前用户信息(用户一旦(通过微信)登录之后，便能获取到用户的信息)
		PersonInfo user = (PersonInfo) request.getSession().getAttribute("user");

		// 判断信息是否为空
		if (username != null && password != null && user != null && user.getUserId() != null) {
			// 创建LocalAuth对象并赋值
			LocalAuth localAuth = new LocalAuth();
			localAuth.setLocatAuthName(username);
			localAuth.setPassWord(password);
			localAuth.setPersoninfo(user);
			// 绑定帐号
			LocalAuthExecution lae = localAuthService.blindLocalAuth(localAuth);
			// 判断绑定是否成功
			if (lae.getState() == LocalAuthStateEnum.SUCCESS.getState()) {
				modelMap.put("success", true);
			} else {
				modelMap.put("success", false);
				modelMap.put("errMsg", lae.getStateInfo());
			}
		} else {
			modelMap.put("success", false);
			modelMap.put("errMsg", "用户名和密码不能为空");
		}
		return modelMap;
	}

	/**
	 * 通过用户名 原密码 修改密码
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/changelocalpwd", method = RequestMethod.POST)
	@ResponseBody
	private Map<String, Object> changeLocalPwd(HttpServletRequest request) {
		Map<String, Object> modelMap = new HashMap<String, Object>();

		// 验证码校验
		if (!CodeUtil.checkVerifyCode(request)) {
			modelMap.put("success", false);
			modelMap.put("errMsg", "输入了错误的验证码");
			return modelMap;
		}

		// 获取帐号
		String username = HttpServletRequestUtil.getString(request, "userName");
		// 获取原先的密码
		String password = HttpServletRequestUtil.getString(request, "password");
		// 获取新密码
		String passwordnew = HttpServletRequestUtil.getString(request, "passwordNew");
		// 从session中获取当前用户信息(用户一旦(通过微信)登录之后，便能获取到用户的信息)
		PersonInfo user = (PersonInfo) request.getSession().getAttribute("user");

		// 非空判断，要求帐号新旧密码以及当前的用户session非空，且新旧密码不相同
		if (username != null && password != null && passwordnew != null && user != null && user.getUserId() != null
				&& !password.equals(passwordnew)) {
			try {
				// 查看原先帐号，看看与输入的帐号是否一致，不一致则认为是非法操作
				LocalAuth la = localAuthService.getLocalAuthByUserId(user.getUserId());
				if (la == null || !la.getLocatAuthName().equals(username)) {
					modelMap.put("success", false);
					modelMap.put("errMsg", "用户名不一致");
					return modelMap;
				}
				// 修改平台帐号的用户密码
				LocalAuthExecution lae = localAuthService.modifyLocalAuth(user.getUserId(), username, password,
						passwordnew);
				// 判断是否修改成功
				if (lae.getState() == LocalAuthStateEnum.SUCCESS.getState()) {
					modelMap.put("success", true);
				} else {
					modelMap.put("success", false);
					modelMap.put("errMsg", "修改失败:" + lae.getStateInfo());
				}
			} catch (Exception e) {
				modelMap.put("success", false);
				modelMap.put("errMsg", "changeLocalPwd:" + e.toString());
				return modelMap;
			}
		} else {
			modelMap.put("success", false);
			modelMap.put("errMsg", "用户名或密码为空");
		}
		return modelMap;
	}

	/**
	 * 登录验证
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/logincheck", method = RequestMethod.POST)
	@ResponseBody
	private Map<String, Object> logincheck(HttpServletRequest request) {
		Map<String, Object> modelMap = new HashMap<String, Object>();
		
		// 获取是否需要进行验证码校验的标识符
		boolean needVerify = HttpServletRequestUtil.getBoolean(request, "needVerify");
		if (needVerify && !CodeUtil.checkVerifyCode(request)) {
			modelMap.put("success", false);
			modelMap.put("errMsg", "输入了错误的验证码");
			return modelMap;
		}
		// 获取输入的帐号
		String userName = HttpServletRequestUtil.getString(request, "userName");
		// 获取输入的密码
		String password = HttpServletRequestUtil.getString(request, "password");
		// 非空校验
		if (userName != null && password != null) {
			// 传入帐号和密码去获取平台帐号信息
			LocalAuth localAuth = localAuthService.getLocalAuthByUsernameAndPwd(userName, password);
			if (localAuth != null) {
				// 若能取到帐号信息则登录成功
				modelMap.put("success", true);
				// 同时在session里设置用户信息
				request.getSession().setAttribute("user", localAuth.getPersoninfo());
			} else {
				modelMap.put("success", false);
				modelMap.put("errMsg", "用户名或密码错误");
			}
		} else {
			modelMap.put("success", false);
			modelMap.put("errMsg", "用户名和密码均不能为空");
		}
		return modelMap;
	}

	/**
	 * 当用户点击登出按钮的时候注销session
	 * 
	 * @param request
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/logout", method = RequestMethod.POST)
	@ResponseBody
	private Map<String, Object> logout(HttpServletRequest request) {
		Map<String, Object> modelMap = new HashMap<String, Object>();
		// 将用户session置为空
		request.getSession().setAttribute("user", null);
		modelMap.put("success", true);
		return modelMap;
	}
}
