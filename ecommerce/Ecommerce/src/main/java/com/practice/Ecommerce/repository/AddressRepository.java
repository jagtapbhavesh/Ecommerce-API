package com.practice.Ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.practice.Ecommerce.Entity.Address;
import java.util.List;


@RepositoryRestResource(path = "address")
public interface AddressRepository extends JpaRepository<Address, Integer>{

	//List<Address> findByCity(String city);
}
