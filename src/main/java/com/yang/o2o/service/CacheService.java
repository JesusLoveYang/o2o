package com.yang.o2o.service;

import org.springframework.stereotype.Service;

@Service
public interface CacheService {

	/**
	 * ����keyǰ׺ɾ��ƥ���ģʽ�µ�����key-value �紫��:shopcategory,��shopcategory_allfirstlevel��
	 * ��shopcategory��ͷ��key_value���ᱻ���
	 * 
	 * @param keyPrefix
	 * @return
	 */
	void removeFromCache(String keyPrefix);
}
