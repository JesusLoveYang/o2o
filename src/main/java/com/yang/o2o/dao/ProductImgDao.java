package com.yang.o2o.dao;

import java.util.List;

import com.yang.o2o.entity.ProductImg;

public interface ProductImgDao {

	List<ProductImg> queryProductImg(long productId);
	/**
	 * 根据商品id 删除商品详情图片
	 * @param productId
	 * @return
	 */
	int deleteProductImgByProductId(long productId);
	
	/**
	 * 批量添加商品图片
	 * 
	 * @param productImgList
	 * @return
	 */
	int insertProductImgs(List<ProductImg> productImgList);
}
