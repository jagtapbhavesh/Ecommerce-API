package com.practice.Ecommerce.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.practice.Ecommerce.Entity.User;
import com.practice.Ecommerce.repository.UserRepository;

@Service
public class CustomerDetailsService implements UserDetailsService{
    
	
	
	
    @Autowired
    UserRepository repository;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		User user= repository.findByEmailId(username).orElseThrow(()->new RuntimeException("Email id not found"));
		return user;
	}

}
