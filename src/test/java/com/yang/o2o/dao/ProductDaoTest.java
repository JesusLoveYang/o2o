package com.yang.o2o.dao;

import java.util.List;

import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;

import com.yang.o2o.BaseTest;
import com.yang.o2o.entity.Product;
import com.yang.o2o.entity.ProductCategory;
import com.yang.o2o.entity.Shop;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ProductDaoTest extends BaseTest{

	@Autowired
	private ProductDao productDao;
	
	@Test
	@Ignore
	public void testUpdateProductCategoryId() {
		int effectNum = productDao.updateProductCategoryToNull(3L);
		System.out.println(effectNum);
	}
	
	@Test
	public void testDQueryProductList() {
		Product product = new Product();
		
		product.setProductName("¹Ï");
		List<Product> productList = productDao.queryProductList(product, 0, 3);
		int effectNum = productDao.queryProductCount(product);
		System.out.println(productList.size());
		System.out.println(effectNum);
	}
	
	@Test
	@Ignore
	public void testAProductDao() {
		
		Product product = new Product();
		
		ProductCategory productcategory = new ProductCategory();
		productcategory.setProductCategoryId(3L);
		
		Shop shop = new Shop();
		shop.setShopId(2L);
		
		product.setProductName("¿§·È");
		product.setProductDes("koufei");
		product.setProductStatus(1);
		product.setProductWeight(60);
		product.setImgAddr("Í¼Æ¬µØÖ·");
		
		product.setProductCategory(productcategory);
		product.setShop(shop);
		
		int effectNum = productDao.insertProducts(product);
		System.out.println(effectNum);
	}
	
	@Test
	@Ignore
	public void testBQueryProductByShopId() {
		long productId = 2L;
		Product product = productDao.queryProductById(productId);
		System.out.println(product.getProductName());
	}
	
	@Test
	@Ignore
	public void testCUpdateProduct() {
		
		Shop shop = new Shop();
		shop.setShopId(2L);
		ProductCategory productcategory = new ProductCategory();
		productcategory.setProductCategoryId(3L);
		
		Product product = new Product();
		product.setProductId(1L);
		product.setShop(shop);
		product.setProductCategory(productcategory);
		
		product.setProductDes("¸ü¸Ä");
		
		int effectNum = productDao.updateProduct(product);
		System.out.println(effectNum);
	}
}
