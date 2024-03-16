package com.practice.Ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.practice.Ecommerce.Entity.Product;
import com.practice.Ecommerce.Projections.ProductProjection;

@RepositoryRestResource(path = "product",excerptProjection = ProductProjection.class)
public interface ProductRepository extends JpaRepository<Product, Integer> {

		
}
