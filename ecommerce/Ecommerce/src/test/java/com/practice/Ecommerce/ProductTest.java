package com.practice.Ecommerce;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.practice.Ecommerce.Entity.Product;
import com.practice.Ecommerce.repository.ProductRepository;

@SpringBootTest
public class ProductTest {
	
	@Autowired
	ProductRepository productRepository;
	
	@Test
	public void testCreateProduct() {
		Product p = new Product();
		p.setId(101);
		p.setName("SunGlasses");
		p.setPrice(20000);
		
		productRepository.save(p);
		assertNotNull(productRepository.findById(p.getId()).get());
	}
}
