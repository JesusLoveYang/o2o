package com.yang.o2o.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yang.o2o.entity.Shop;

public interface ShopDao {
	
	/**
	 *  ��ҳ��ѯ����������������������֣�ģ������״̬�������������id��owner_id��  ������ϲ�ѯ
	 * 
	 * @param shopCondition
	 * @param initIndex  �ӵڼ��п�ʼȡ����
	 * @param pageSize   ȡ������
	 * @return
	 */
	List<Shop> queryShopList(@Param("shopCondition") Shop shopCondition, @Param("initIndex") int initIndex,
			@Param("pageSize") int pageSize);
	
	/**
	 * ��ȡqueryShopList����
	 * @return
	 */
	int queryShopCount(@Param("shopCondition") Shop shopCondition);
	
	/**
	 *    ͨ��shopId ��ѯ����
	 */
	Shop queryById(long shopId);
	
	/**
	 * ��������
	 * @param shop
	 * @return
	 */
	int insertShop(Shop shop);
	
	/**
	 * ���µ���
	 */
	int updateShop(Shop shop);
}
