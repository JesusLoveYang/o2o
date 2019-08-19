package com.yang.o2o.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yang.o2o.dao.ProductDao;
import com.yang.o2o.dao.ProductImgDao;
import com.yang.o2o.dto.ImageHolder;
import com.yang.o2o.dto.ProductExecution;
import com.yang.o2o.entity.Product;
import com.yang.o2o.entity.ProductImg;
import com.yang.o2o.enums.ProductStateEnum;
import com.yang.o2o.service.ProductService;
import com.yang.o2o.util.ImageUtil;
import com.yang.o2o.util.PageCount;
import com.yang.o2o.util.PathUtil;

@Service
public class ProductServiceImpl implements ProductService{

	@Autowired
	private ProductDao productDao;
	
	@Autowired
	private ProductImgDao productImgDao;
	
	@Override
	@Transactional
	// 1.��������ͼ����ȡ����ͼ���·������ֵ��product
	// 2.��tb_productд����Ʒ��Ϣ����ȡproductId
	// 3.���productId����������Ʒ����ͼ
	// 4.����Ʒ����ͼ�б���������tb_product_img��
	public ProductExecution addProduct(Product product, ImageHolder thumbnail, List<ImageHolder> productImgHolderList) {
		//��ֵ�ж�
		if (product != null && product.getShop() != null && product.getShop().getShopId() > 0) {
			// ����Ʒ������Ĭ������
			product.setCreateTime(new Date());
			product.setAlterTime(new Date());
			
			// Ĭ��Ϊ�ϼܵ�״̬
			product.setProductStatus(1);
			// ����Ʒ����ͼ��Ϊ�������
			if (thumbnail != null) {
				addThumbnail(product, thumbnail);
			}
			try {
				//�����Ʒ������ͼ
				int effectedNum = productDao.insertProducts(product);
				if (effectedNum <= 0) {
					throw new RuntimeException("�����Ʒʧ��");
				}
			} catch (Exception e) {
				throw new RuntimeException("addProduct error:" + e.getMessage());
			}
			// ����Ʒ����ͼ��Ϊ�������
			if (productImgHolderList != null && productImgHolderList.size() > 0) {
				addProductImgList(product, productImgHolderList);
			}
			return new ProductExecution(ProductStateEnum.SUCCESS, product);
		}else {
			// ����Ϊ���򷵻ؿ�ֵ������Ϣ
			return new ProductExecution(ProductStateEnum.EMPTY);
		}
	}

	/**
	 * ���������Ʒ
	 * @param product
	 * @param productImgHolderList
	 */
	private void addProductImgList(Product product, List<ImageHolder> productImgHolderList) {
		// ��ȡͼƬ�洢·��������ֱ�Ӵ�ŵ���Ӧ���̵��ļ��е���
		String dest = PathUtil.getShopImgPath(product.getShop().getShopId());
		List<ProductImg> productImgList = new ArrayList<ProductImg>();
		// ����ͼƬһ��ȥ��������ӽ�productImgʵ������
		for (ImageHolder productImgHolder : productImgHolderList) {
			String imgAddr = ImageUtil.generateNormalImg(productImgHolder, dest);
			ProductImg productImg = new ProductImg();
			productImg.setProductImgAddr(imgAddr);
			productImg.setProductId(product.getProductId());
			productImg.setCreateTime(new Date());
			productImgList.add(productImg);
		}
		
		// ���ȷʵ����ͼƬ��Ҫ��ӵģ���ִ��������Ӳ���
		if (productImgList.size() > 0) {
			try {
				int effectedNum = productImgDao.insertProductImgs(productImgList);
				if (effectedNum <= 0) {
					throw new RuntimeException("������Ʒ����ͼƬʧ��");
				}
			} catch (Exception e) {
				throw new RuntimeException("������Ʒ����ͼƬʧ��:" + e.toString());
			}
		}
	}

	/**
	 * �����Ʒ����ͼ
	 * @param product
	 * @param thumbnail
	 */
	private void addThumbnail(Product product, ImageHolder thumbnail) {
		String dest = PathUtil.getShopImgPath(product.getShop().getShopId());
		String thumbnailAddr = ImageUtil.generateThumbnail(thumbnail, dest);
		product.setImgAddr(thumbnailAddr);
	}

	/**
	 * �޸���Ʒ��Ϣ �� ͼƬ
	 */
	@Override
	@Transactional
	// 1.������ͼ������ֵ����������ͼ��
	// ��ԭ�ȴ�������ͼ����ɾ���������ͼ��֮���ȡ����ͼ���·������ֵ��product
	// 2.����Ʒ����ͼ�б������ֵ������Ʒ����ͼƬ�б����ͬ���Ĳ���
	// 3.��tb_product_img����ĸ���Ʒԭ�ȵ���Ʒ����ͼ��¼ȫ�����
	// 4.����tb_product_img�Լ�tb_product����Ϣ
	public ProductExecution modifyProduct(Product product, ImageHolder imageHolder,
			List<ImageHolder> productImageHolder) {
		//��ֵ�ж�
		if (product != null && product.getShop() != null && product.getShop().getShopId() >= 0) {
			// ����Ʒ������Ĭ������
			product.setAlterTime(new Date());
			// ����Ʒ����ͼ��Ϊ����ԭ������ͼ��Ϊ����ɾ��ԭ������ͼ�����
			if (imageHolder != null) {
				// �Ȼ�ȡһ��ԭ����Ϣ����Ϊԭ������Ϣ����ԭͼƬ��ַ
				Product tempProduct = productDao.queryProductById(product.getProductId());
				if (tempProduct.getImgAddr() != null) {
					ImageUtil.deleteFileOrPath(tempProduct.getImgAddr());
				}
				addThumbnail(product, imageHolder);
			}
			// ������´������Ʒ����ͼ�������productId��ԭ�ȵ�ɾ����������µ�ͼƬ
			if (productImageHolder != null && productImageHolder.size() >= 0) {
				deleteProductImgList(product.getProductId());
				addProductImgList(product, productImageHolder);
			}
			try {
				int effectedNum = productDao.updateProduct(product);
				if (effectedNum <= 0) {
					throw new RuntimeException("������Ʒʧ��");
				}else {
					return new ProductExecution(ProductStateEnum.SUCCESS, product);
				}
			} catch (Exception e) {
				throw new RuntimeException("modifyProduct error:" + e.toString());
			}
		}else {
			return new ProductExecution(ProductStateEnum.EMPTY);
		}
	}

	private void deleteProductImgList(Long productId) {
		// ����productId��ȡԭ����ͼƬ
		List<ProductImg> productImgList = productImgDao.queryProductImg(productId);
		//����ɾ��ͼƬ
		for (ProductImg productImg : productImgList) {
			ImageUtil.deleteFileOrPath(productImg.getProductImgAddr());
		}
		//ɾ�����ݿ��е�ͼƬ
		productImgDao.deleteProductImgByProductId(productId);
	}

	/*
	 * ͨ��productid��ȡ��Ʒ��Ϣ
	 */
	@Override
	public Product getProduct(long productId) {
		return productDao.queryProductById(productId);
	}

	/**
	 * ��ҳ��ѯ�����ݲ�ѯ������ȡ��Ʒ���� �� �б�
	 */
	public ProductExecution getProductList(Product productCondition, int pageIndex, int pageSize) {
		int rowIndex = PageCount.pageIndexToRow(pageIndex, pageSize); //��ȡ���Ŀ�ʼȡ����
		List<Product> productlList = productDao.queryProductList(productCondition, rowIndex, pageSize); //��ȡ�����б�
		int effectedNum = productDao.queryProductCount(productCondition);
		
		ProductExecution se = new ProductExecution();
		if (productlList != null) {
			se.setProductList(productlList);
			se.setCount(effectedNum);
		}else {
			se.setStateInfo(ProductStateEnum.EMPTY.getStateInfo());
		}
		return se;
	}
}
