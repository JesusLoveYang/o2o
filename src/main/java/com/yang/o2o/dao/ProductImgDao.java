package com.yang.o2o.dao;

import java.util.List;

import com.yang.o2o.entity.ProductImg;

public interface ProductImgDao {

	List<ProductImg> queryProductImg(long productId);
	/**
	 * ������Ʒid ɾ����Ʒ����ͼƬ
	 * @param productId
	 * @return
	 */
	int deleteProductImgByProductId(long productId);
	
	/**
	 * ���������ƷͼƬ
	 * 
	 * @param productImgList
	 * @return
	 */
	int insertProductImgs(List<ProductImg> productImgList);
}
