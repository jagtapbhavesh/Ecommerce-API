package com.practice.Ecommerce.Dto;

import java.util.HashSet;
import java.util.Set;

import com.practice.Ecommerce.Entity.Address;
import com.practice.Ecommerce.Entity.Roles;
import com.practice.Ecommerce.validators.PasswordMatch;
import com.practice.Ecommerce.validators.PasswordValid;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@PasswordMatch
public class UserDto {
	
	private String id;
	@NotNull(message = "Name cannot be null")
	@NotBlank(message = "Please enter name")
	@Size(min = 6,max =30 ,message = "min 6 nax 20")
	private String name;
	@Email
	private String emailId;
	
	@PasswordValid
	private String password;

	private String confirmPassword;
	private String about;
	
	private String image;
	@Min(18)
	@Max(100)
	private int age;
	private Address address;
	private Set<Roles> roles= new HashSet<Roles>();
	

}
