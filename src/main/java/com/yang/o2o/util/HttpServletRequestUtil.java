package com.yang.o2o.util;

import javax.servlet.http.HttpServletRequest;

/**
 * ���� HttpServletRequest����
 * @author Yang
 *
 */
public class HttpServletRequestUtil {
	
	//request���кܶ�key value�� ͨ��key ȡ��value������ת����Ҫ������
	public static int getInt(HttpServletRequest request, String key) {
		try {
			return Integer.parseInt(request.getParameter(key));
		} catch (NumberFormatException e) {
			return -1;
		}
	}
	
	//��ȡ������
	public static Long getLong(HttpServletRequest request, String key) {
		try {
			return Long.valueOf(request.getParameter(key));
		} catch (NumberFormatException e) {
			return -1L;
		}
	}
	
	//��ȡdouble
	public static Double getDouble(HttpServletRequest request, String key) {
		try {
			return Double.valueOf(request.getParameter(key));
		} catch (NumberFormatException e) {
			return -1d;
		}
	}
	
	//��ȡboolean
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
