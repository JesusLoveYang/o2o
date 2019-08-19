package com.yang.o2o.entity;

import java.io.IOException;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Demo {

	public static void main(String[] args) throws JsonParseException, JsonMappingException, IOException {
		
		Product product = new Product();
		
	    String str ="{\"productName\":\"12\",\"productDes\":\"12\",\"productWeight\":\"3\","
		 		+ "\"normalPrice\":\"2\",\"reducePrice\":\"1\",\"productCategory\":{\"productCategoryId\":4},\"productId\":\"\"}";
		System.out.println(str);
		
		ObjectMapper mapper = new ObjectMapper();
		mapper.setSerializationInclusion(Include.ALWAYS);
		product = mapper.readValue(str, Product.class);
		
		System.out.println(product.getCreateTime());
	}

}
