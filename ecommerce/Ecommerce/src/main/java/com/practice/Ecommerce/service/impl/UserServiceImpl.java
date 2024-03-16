package com.practice.Ecommerce.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.practice.Ecommerce.Dto.UserDto;
import com.practice.Ecommerce.Entity.Roles;
import com.practice.Ecommerce.Entity.User;
import com.practice.Ecommerce.Exceptions.IdNotFoundException;
import com.practice.Ecommerce.repository.RoleRepository;
import com.practice.Ecommerce.repository.UserRepository;
import com.practice.Ecommerce.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository repository;
	
	@Autowired
	PasswordEncoder encoder;
	
	@Autowired
	RoleRepository roleRepository;
	

	@Override
	public UserDto createUser(UserDto dto) {
		String id = UUID.randomUUID().toString();
		// dto is a object given by user
		dto.setId(id);
		User u = dtoToEntity(dto);
		Roles role = roleRepository.findById(1)
		.orElseThrow(()->new RuntimeException("ID not found"));
		
		u.getRoles().add(role);
		
		
		User SavedUser = repository.save(u);// Object which has been saved in your db
		UserDto SavedDto = entityToDto(SavedUser);
		return SavedDto;
	}

	@Override
	public void deleteUser(String id) {
	 User user = repository.findById(id)
	 .orElseThrow(()->new IdNotFoundException("Id not found"));
	 
	 repository.delete(user);

	}

	@Override
	public UserDto updateUser(String id, UserDto dto) {
		 
         User user = repository.findById(id)
         .orElseThrow(()->new IdNotFoundException("Id not Found"));
         
         user.setName(dto.getName());
         user.setEmailId(dto.getEmailId());
         user.setPassword(dto.getPassword());
         user.setAbout(dto.getAbout());
         user.setImage(dto.getImage());
         
         User savedUser = repository.save(user);
         UserDto SavedDto = entityToDto(savedUser);
         
		return SavedDto;
	}

	@Override
	public List<UserDto> getAllUsers() {
	   List<User> users = repository.findAll();
	 
	   List<UserDto> UserdtoList = users.stream()
	   .map((user)->entityToDto(user))
	   .collect(Collectors.toList());
		
		return UserdtoList;
	}

	@Override
	public UserDto getById(String id) {
		User user = repository.findById(id)
				
		.orElseThrow(()->new IdNotFoundException("id not found"));	
		
		UserDto UserDto = entityToDto(user);	
		return UserDto;
	}
	
	
	

	public User dtoToEntity(UserDto dto) {
		User u = new User();
		u.setId(dto.getId());
		u.setName(dto.getName());
		u.setAbout(dto.getAbout());
		u.setPassword(encoder.encode(dto.getPassword()));
		u.setEmailId(dto.getEmailId());
		u.setImage(dto.getImage());
		u.setAddress(dto.getAddress());
	
		return u;
	}

	public UserDto entityToDto(User u) {
		UserDto dto = new UserDto();

		dto.setId(u.getId());
		dto.setName(u.getName());
		dto.setAbout(u.getAbout());
		dto.setEmailId(u.getEmailId());
		dto.setPassword(u.getPassword());
		dto.setImage(u.getImage());
		dto.setAddress(u.getAddress());
		dto.setRoles(u.getRoles());

		return dto;

	}

	@Override
	public UserDto getByEmail(String email) {
		User user = repository.findByEmailId(email)
		.orElseThrow(()->new RuntimeException("Email Id not found"));
		
		UserDto Userdto = entityToDto(user);
		return Userdto;
	}

	@Override
	public List<UserDto> getByKeyword(String keyword) {
		
		List<User> users = repository.findByNameContaining(keyword)
		.orElseThrow(()->new  RuntimeException("Keyword not found"));
		
		List<UserDto> userDtoList = users
				.stream()
				.map((user)->entityToDto(user))
				.collect(Collectors.toList());
	
		return userDtoList;
	}
	
	
	
	
	
	

	
	
}
