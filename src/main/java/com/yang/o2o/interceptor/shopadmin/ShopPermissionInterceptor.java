package com.yang.o2o.interceptor.shopadmin;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.yang.o2o.entity.Shop;

/**
 * ��ҹ���ϵͳ������֤������
 * 
 * @author Yang
 *
 */
public class ShopPermissionInterceptor extends HandlerInterceptorAdapter {

	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {
		// ��session�л�ȡ��ǰѡ��ĵ���
		Shop currentShop = (Shop) request.getSession().getAttribute("currentShop");
		// ��session�л�ȡ��ǰ�û��ɲ����ĵ����б�
		@SuppressWarnings("unchecked")
		List<Shop> shopList = (List<Shop>) request.getSession().getAttribute("shopList");
		// �ǿ��ж�
		if (currentShop != null && shopList != null) {
			// �����ɲ����ĵ����б�
			for (Shop shop : shopList) {
				// �����ǰ�����ڿɲ������б����򷵻�true�����н��������û�����
				if (shop.getShopId() == currentShop.getShopId()) {
					return true;
				}
			}
		}
		// ������������������֤�򷵻�false,��ֹ�û�������ִ��
		return false;
	}
}