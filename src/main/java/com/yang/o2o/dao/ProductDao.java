package com.yang.o2o.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yang.o2o.entity.Product;

public interface ProductDao {

	/**
	 * 分页查询，可输入的条件 商品名字（模糊）、商品状态、商品类型、店铺id
	 * 
	 * @param productCondition
	 * @param initIndex
	 * @param pageSize
	 * @return
	 */
	List<Product> queryProductList(@Param("productCondition") Product productCondition,
			@Param("initIndex") int initIndex, @Param("pageSize") int pageSize);

	/**
	 * 查询对应商品总数
	 * 
	 * @param productCondition
	 * @return
	 */
	int queryProductCount(@Param("productCondition") Product productCondition);

	/**
	 * 根据传入的商品实例 更新商品
	 * 
	 * @param product
	 * @return
	 */
	int updateProduct(Product product);

	/**
	 * 通过商品id获取唯一的商品信息
	 * 
	 * @param productId
	 * @return
	 */
	Product queryProductById(long productId);

	/**
	 * 插入商品
	 * 
	 * @param product
	 * @return
	 */
	int insertProducts(Product product);

	/**
	 * 删除商品分类时，也要将商品所属的分类置为null
	 * 
	 * @param productCategoryId
	 * @return
	 */
	int updateProductCategoryToNull(long productCategoryId);
}
