package com.yang.o2o.util;

import javax.servlet.http.HttpServletRequest;

public class CodeUtil {
	/**
	 * �ж��������֤���Ƿ���ȷ
	 */
	public static boolean checkVerifyCode(HttpServletRequest request) {
		// ��ȡͼƬ�е���֤��. getSession�ӻỰ�л�ȡ
		String verifyCodeException = (String) request.getSession()
				.getAttribute(com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY);
		//�������л�ȡ��֤��
		String verifyCodeActual = HttpServletRequestUtil.getString(request, "verifyCodeActual");
		//�Ա���֤���Ƿ����
		if(verifyCodeActual != null && verifyCodeException.equals(verifyCodeActual)) {
			return true;
		}else {
			return false;
		}
		
	}
}
