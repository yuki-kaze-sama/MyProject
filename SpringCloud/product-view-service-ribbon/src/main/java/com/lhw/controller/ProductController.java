package com.lhw.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lhw.pojo.Product;
import com.lhw.service.ProductService;

@Controller
public class ProductController {

	@Autowired 
	private ProductService productService;
	
	@RequestMapping("/products")
	public Object products(Model m) {
		 List<Product> ps = productService.listProducts();
	        m.addAttribute("ps", ps);
	        return "products";
	}
}
