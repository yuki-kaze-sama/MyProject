package com.lhw.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lhw.client.ProductClientFeign;
import com.lhw.pojo.Product;

@Service
public class ProductService {
	@Autowired 
	private ProductClientFeign productClientFeign;
	
	public List<Product> listProducts(){
		return productClientFeign.listProducts();
	}

}
