package com.lhw.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lhw.client.ProductClientRibbon;
import com.lhw.pojo.Product;

@Service
public class ProductService {
	
	@Autowired 
	private ProductClientRibbon productClientRibbon;
	
	public List<Product> listProducts(){
		return productClientRibbon.listProdcuts();
	}
}
