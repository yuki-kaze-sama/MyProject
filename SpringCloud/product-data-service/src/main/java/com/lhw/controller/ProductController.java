package com.lhw.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lhw.pojo.Product;
import com.lhw.service.ProductService;

@RestController
public class ProductController {
	
	@Autowired 
	private ProductService productService;
	
	@RequestMapping("/products")
	public Object products() {
		List<Product> ps = productService.listProducts();
		return ps;
	}
}
