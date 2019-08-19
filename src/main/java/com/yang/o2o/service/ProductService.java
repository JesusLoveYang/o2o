package com.yang.o2o.service;

import java.util.List;

import com.yang.o2o.dto.ImageHolder;
import com.yang.o2o.dto.ProductExecution;
import com.yang.o2o.entity.Product;

public interface ProductService {

	/**
	 * ��ҳ��ѯ��������Ʒ��������ѯ��������������У���Ʒ״̬����Ʒ���� ����id ��Ʒ����ģ����ѯ
	 * 
	 * @param productCondition
	 * @param initIndex
	 * @param pageSize
	 * @return
	 */
	ProductExecution getProductList(Product productCondition, int initIndex, int pageSize);

	/**
	 * �����Ʒ��Ϣ �� ͼƬ����
	 * 
	 * @param product
	 * @param imageHolder
	 * @param productImageHolder
	 * @return
	 */
	ProductExecution modifyProduct(Product product, ImageHolder imageHolder, List<ImageHolder> productImageHolder);

	/**
	 * ����productId��ȡ��Ʒ
	 * 
	 * @param productId
	 * @return
	 */
	Product getProduct(long productId);

	/**
	 * �����Ʒ ��������ͼ ��ƷͼƬ�б�
	 * 
	 * @param product
	 * @param thumbnail
	 * @param productImgList
	 * @return
	 */
	ProductExecution addProduct(Product product, ImageHolder thumbnail, List<ImageHolder> productImgList);
}
