package com.yang.o2o.interceptor.shopadmin;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.yang.o2o.entity.PersonInfo;

/**
 * ��ҹ���ϵͳ��¼��֤������
 * @author Yang
 *
 */
public class ShopLoginInterceptor extends HandlerInterceptorAdapter{
	
	/**
	 * ������Ҫ�ı�ķ����� ��ǰ����
	 * ���û���������ǰ����дpreHandle����߼�����������
	 * @throws IOException 
	 * 
	 * ��������������Ҫ���ص�controller
	 */
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {
		// ��session��ȡ���û���Ϣ��
		Object userObj = request.getSession().getAttribute("user");
		if (userObj != null) {
			// ���û���Ϣ��Ϊ����session����û���Ϣת����PersonInfoʵ�������
			PersonInfo user = (PersonInfo)userObj;
			// ����ֵ�жϣ�ȷ��userId��Ϊ�ղ��Ҹ��ʺŵĿ���״̬Ϊ1�������û�����Ϊ���
			if (user != null && user.getUserId() != null && user.getUserId() > 0 && user.getStatus() == 1) {
				// ��ͨ����֤�򷵻�true,����������true֮���û��������Ĳ�����������ִ��
				return true;
			}
		}
		// ���������¼��֤����ֱ����ת���ʺŵ�¼ҳ��
		PrintWriter out = response.getWriter();
		out.println("<html>");
		out.println("<script>");
		out.println("window.open ('" + request.getContextPath() + "/local/login?usertype=2','_self')");
		out.println("</script>");
		out.println("</html>");
		return false;
	}
}
