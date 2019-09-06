package com.lhw.client;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.lhw.pojo.Product;

@Component
public class ProductClientRibbon {
 
    @Autowired
    RestTemplate restTemplate;
 
    @SuppressWarnings("unchecked")
	public List<Product> listProdcuts() {
        return restTemplate.getForObject("http://PRODUCT-DATA-SERVICE/products",List.class);
    }
 
}
