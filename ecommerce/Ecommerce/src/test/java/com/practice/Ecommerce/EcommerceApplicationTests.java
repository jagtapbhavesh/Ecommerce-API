package com.practice.Ecommerce;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.practice.Ecommerce.Entity.Calculator;
import com.practice.Ecommerce.Entity.Category;
import com.practice.Ecommerce.Entity.Product;
import com.practice.Ecommerce.repository.CategoryRepository;
import com.practice.Ecommerce.repository.ProductRepository;

@SpringBootTest
class EcommerceApplicationTests {
Calculator c= new Calculator();
	@Test
	void contextLoads() {
		System.out.println("testing started");
	}

	@Test
	void testAdd() {
		int actualResult = c.add(12,10);
		int expectedResult = 2;
		assertEquals(expectedResult, actualResult);
	}
	
	@Test
	void testSub() {
		int actualResult = c.sub(12,10);
		int expectedResult = 22;
		assertEquals(expectedResult, actualResult);
	}
	
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
	
	@Test
	public void testAllProducts() {
		List<Product> allProduct = productRepository.findAll();
		assertTrue(allProduct.size()>0);
	}
	
	@Test
	public void deleteProduct() {
		productRepository.deleteById(101);
		assertFalse(productRepository.existsById(101));
	}
	
	@Autowired
	CategoryRepository categoryRepository;
	
	@Test
	public void testCreateCategory() {
		Category c = new Category();
		c.setId(3);
		c.setTitle("Accessories");
		c.setDescription("Sunglasses , Watches , Belts etc");
		
		categoryRepository.save(c);
		assertNotNull(categoryRepository.findById(c.getId()).get());
	}
}
