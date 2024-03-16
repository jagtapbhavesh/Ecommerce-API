package com.practice.Ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.practice.Ecommerce.Entity.User;
import java.util.List;
import java.util.Optional;


public interface UserRepository extends JpaRepository<User,String> {
	
     Optional< User>findByEmailId(String emailId);
     Optional< List<User>> findByNameContaining(String keyword);
     
     
     

}
