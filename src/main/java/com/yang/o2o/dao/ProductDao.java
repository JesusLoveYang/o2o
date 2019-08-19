package com.yang.o2o.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yang.o2o.entity.Product;

public interface ProductDao {

	/**
	 * ��ҳ��ѯ������������� ��Ʒ���֣�ģ��������Ʒ״̬����Ʒ���͡�����id
	 * 
	 * @param productCondition
	 * @param initIndex
	 * @param pageSize
	 * @return
	 */
	List<Product> queryProductList(@Param("productCondition") Product productCondition,
			@Param("initIndex") int initIndex, @Param("pageSize") int pageSize);

	/**
	 * ��ѯ��Ӧ��Ʒ����
	 * 
	 * @param productCondition
	 * @return
	 */
	int queryProductCount(@Param("productCondition") Product productCondition);

	/**
	 * ���ݴ������Ʒʵ�� ������Ʒ
	 * 
	 * @param product
	 * @return
	 */
	int updateProduct(Product product);

	/**
	 * ͨ����Ʒid��ȡΨһ����Ʒ��Ϣ
	 * 
	 * @param productId
	 * @return
	 */
	Product queryProductById(long productId);

	/**
	 * ������Ʒ
	 * 
	 * @param product
	 * @return
	 */
	int insertProducts(Product product);

	/**
	 * ɾ����Ʒ����ʱ��ҲҪ����Ʒ�����ķ�����Ϊnull
	 * 
	 * @param productCategoryId
	 * @return
	 */
	int updateProductCategoryToNull(long productCategoryId);
}
