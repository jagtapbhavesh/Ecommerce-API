package com.practice.Ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.practice.Ecommerce.Entity.Roles;

public interface RoleRepository extends JpaRepository<Roles,Integer >{
	

}
