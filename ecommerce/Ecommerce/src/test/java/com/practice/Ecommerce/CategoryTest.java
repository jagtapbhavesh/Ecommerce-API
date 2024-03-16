package com.practice.Ecommerce;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.practice.Ecommerce.Entity.Category;
import com.practice.Ecommerce.repository.CategoryRepository;

@SpringBootTest
public class CategoryTest {
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
