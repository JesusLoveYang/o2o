package com.yang.o2o.util;

import javax.servlet.http.HttpServletRequest;

public class CodeUtil {
	/**
	 * 判断输入的验证码是否正确
	 */
	public static boolean checkVerifyCode(HttpServletRequest request) {
		// 获取图片中的验证码. getSession从会话中获取
		String verifyCodeException = (String) request.getSession()
				.getAttribute(com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY);
		//从输入中获取验证码
		String verifyCodeActual = HttpServletRequestUtil.getString(request, "verifyCodeActual");
		//对比验证码是否相等
		if(verifyCodeActual != null && verifyCodeException.equals(verifyCodeActual)) {
			return true;
		}else {
			return false;
		}
		
	}
}
