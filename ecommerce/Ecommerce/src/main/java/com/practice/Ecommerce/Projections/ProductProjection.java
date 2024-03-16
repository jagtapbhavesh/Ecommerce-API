package com.practice.Ecommerce.Projections;

import org.springframework.data.rest.core.config.Projection;

import com.practice.Ecommerce.Entity.Product;

@Projection(types = Product.class)
public interface ProductProjection {
	
	int getId();
	//String getName();
	int getPrice();
	
	

}
