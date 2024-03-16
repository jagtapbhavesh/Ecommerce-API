package com.practice.Ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.practice.Ecommerce.Entity.Orders;

@RepositoryRestResource(path = "order")
public interface OrderRepository extends JpaRepository<Orders, Integer> {

}
