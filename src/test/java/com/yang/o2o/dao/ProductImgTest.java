package com.yang.o2o.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;

import com.yang.o2o.BaseTest;
import com.yang.o2o.entity.ProductImg;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ProductImgTest extends BaseTest{

	@Autowired
	private ProductImgDao productImgDao;
	
	@Test
	public void testAProductImgDao() {
		
		List<ProductImg> list = new ArrayList<ProductImg>();
		
		ProductImg productImgA = new ProductImg();
		ProductImg productImgB = new ProductImg();
		
		productImgA.setProductImgAddr("µÿ÷∑C");
		productImgA.setProductImgDes("√Ë ˆC");
		productImgA.setProductWeight(20);
		productImgA.setCreateTime(new Date());
		productImgA.setProductId(1L);
		
		productImgB.setProductImgAddr("µÿ÷∑D");
		productImgB.setProductImgDes("√Ë ˆD");
		productImgB.setProductWeight(30);
		productImgB.setCreateTime(new Date());
		productImgB.setProductId(1L);
		
		list.add(productImgA);
		list.add(productImgB);
		
		int effectNum = productImgDao.insertProductImgs(list);
		
		System.out.println(effectNum);
	}
	
	@Test
	public void testBDeleteProducImg() {
		long productId = 1L;
		int effectedNum = productImgDao.deleteProductImgByProductId(productId);
		System.out.println(effectedNum);
	}
}
