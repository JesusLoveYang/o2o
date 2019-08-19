package com.yang.o2o.util;

import javax.servlet.http.HttpServletRequest;

/**
 * 处理 HttpServletRequest参数
 * @author Yang
 *
 */
public class HttpServletRequestUtil {
	
	//request中有很多key value， 通过key 取出value，将其转成需要的类型
	public static int getInt(HttpServletRequest request, String key) {
		try {
			return Integer.parseInt(request.getParameter(key));
		} catch (NumberFormatException e) {
			return -1;
		}
	}
	
	//获取长整型
	public static Long getLong(HttpServletRequest request, String key) {
		try {
			return Long.valueOf(request.getParameter(key));
		} catch (NumberFormatException e) {
			return -1L;
		}
	}
	
	//获取double
	public static Double getDouble(HttpServletRequest request, String key) {
		try {
			return Double.valueOf(request.getParameter(key));
		} catch (NumberFormatException e) {
			return -1d;
		}
	}
	
	//获取boolean
	public static Boolean getBoolean(HttpServletRequest request, String key) {
		try {
			return Boolean.valueOf(request.getParameter(key));
		} catch (NumberFormatException e) {
			return false;
		}
	}
	
	public static String getString(HttpServletRequest request, String key) {
		try {
			String result = request.getParameter(key);
			if (result != null) {
				return result.trim();
			}
			if("".equals(request)) {
				return null;
			}
			return result;
		} catch (Exception e) {
			return null;
		}
	}
}
