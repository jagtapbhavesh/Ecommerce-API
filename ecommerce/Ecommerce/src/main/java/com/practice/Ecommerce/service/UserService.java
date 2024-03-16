package com.practice.Ecommerce.service;

import java.util.List;

import com.practice.Ecommerce.Dto.UserDto;

public interface UserService {
	
	//To create User
	UserDto createUser(UserDto dto);
	
	//To delete User
	void deleteUser(String id);
	
	//To Update User
	
	 UserDto updateUser(String id,UserDto dto);
	
	
	//To get all users
	
	  List<UserDto> getAllUsers();
	  
	 //get user by id
	  
	 UserDto getById(String id);
	 
	 
	 //Get By email
	 
	 UserDto getByEmail(String email);
	 
	 //Get users by keyword
	  List<UserDto> getByKeyword(String keyword);

}
