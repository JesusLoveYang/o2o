package com.yang.o2o.service;

import java.util.List;

import com.yang.o2o.dto.ImageHolder;
import com.yang.o2o.dto.ProductExecution;
import com.yang.o2o.entity.Product;

public interface ProductService {

	/**
	 * 分页查询，根据商品的条件查询，可输入的条件有：商品状态，商品分类 店铺id 商品名字模糊查询
	 * 
	 * @param productCondition
	 * @param initIndex
	 * @param pageSize
	 * @return
	 */
	ProductExecution getProductList(Product productCondition, int initIndex, int pageSize);

	/**
	 * 添加商品信息 及 图片处理
	 * 
	 * @param product
	 * @param imageHolder
	 * @param productImageHolder
	 * @return
	 */
	ProductExecution modifyProduct(Product product, ImageHolder imageHolder, List<ImageHolder> productImageHolder);

	/**
	 * 根据productId获取商品
	 * 
	 * @param productId
	 * @return
	 */
	Product getProduct(long productId);

	/**
	 * 添加商品 处理缩略图 商品图片列表
	 * 
	 * @param product
	 * @param thumbnail
	 * @param productImgList
	 * @return
	 */
	ProductExecution addProduct(Product product, ImageHolder thumbnail, List<ImageHolder> productImgList);
}
