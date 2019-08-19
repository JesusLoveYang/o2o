package com.yang.o2o.service;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.yang.o2o.BaseTest;
import com.yang.o2o.dto.ImageHolder;
import com.yang.o2o.dto.ProductExecution;
import com.yang.o2o.entity.Product;
import com.yang.o2o.entity.ProductCategory;
import com.yang.o2o.entity.Shop;
import com.yang.o2o.enums.ProductStateEnum;

public class ProductServiceTest extends BaseTest{

	@Autowired
	private ProductService productService;
	
	@Test
	public void testBmodifyProduct() throws FileNotFoundException {
		Shop shop = new Shop();
		shop.setShopId(2L);
		ProductCategory productCategory = new ProductCategory();
		productCategory.setProductCategoryId(3L);
		
		Product product = new Product();
		product.setShop(shop);
		product.setProductCategory(productCategory);
		
		product.setProductId(1L);
		product.setProductDes("正是商品");
		
		// 创建缩略图文件流
		File thumbnailFile = new File("F:\\projectimage\\123.jpg");
		InputStream is = new FileInputStream(thumbnailFile);
		ImageHolder imageHolder = new ImageHolder(thumbnailFile.getName(), is);
		
		//上传图片
		List<ImageHolder> productImgList = new ArrayList<ImageHolder>();
		
		File productImgA = new File("F:\\projectimage\\cars.png");
		InputStream isA = new FileInputStream(productImgA);
		File productImgB = new File("F:\\projectimage\\jita.png");
		InputStream isB = new FileInputStream(productImgB);
		
		productImgList.add(new ImageHolder(productImgA.getName(), isA));
		productImgList.add(new ImageHolder(productImgB.getName(), isB));
		
		ProductExecution pe = productService.modifyProduct(product, imageHolder, productImgList);
		System.out.println(pe.getState());
		assertEquals(ProductStateEnum.SUCCESS.getState(), pe.getState());
	}
	
	@Test
	@Ignore
	public void testAddProduct() throws FileNotFoundException {
		Product product = new Product();
		
		Shop shop = new Shop();
		shop.setShopId(2L);
		product.setShop(shop);
		
		ProductCategory productCategory = new ProductCategory();
		productCategory.setProductCategoryId(4L);
		product.setProductCategory(productCategory);
		
		product.setProductName("西红柿");
		product.setProductDes("很好吃的哦");
		product.setProductWeight(20);
		product.setProductStatus(ProductStateEnum.SUCCESS.getState());
		
		//上传缩略图
		File thumbnailFile = new File("F:\\projectimage\\james.jpg");
		InputStream is = new FileInputStream(thumbnailFile);
		ImageHolder imageHolder = new ImageHolder(thumbnailFile.getName(), is);
		
		//上传图片
		List<ImageHolder> productImgList = new ArrayList<ImageHolder>();
		
		File productImgA = new File("F:\\projectimage\\car.jpg");
		InputStream isA = new FileInputStream(productImgA);
		File productImgB = new File("F:\\projectimage\\twocar.jpg");
		InputStream isB = new FileInputStream(productImgB);
		
		productImgList.add(new ImageHolder(productImgA.getName(), isA));
		productImgList.add(new ImageHolder(productImgB.getName(), isB));
		
		ProductExecution pe = productService.addProduct(product, imageHolder, productImgList);
		assertEquals(ProductStateEnum.SUCCESS.getState(), pe.getState());
	}
}
