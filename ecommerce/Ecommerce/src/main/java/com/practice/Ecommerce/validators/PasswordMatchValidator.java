package com.practice.Ecommerce.validators;

import com.practice.Ecommerce.Dto.UserDto;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PasswordMatchValidator implements ConstraintValidator<PasswordMatch, UserDto>{

	@Override
	public boolean isValid(UserDto dto, ConstraintValidatorContext context) {
		
		return dto.getPassword().equals(dto.getConfirmPassword()) ;
	}

}
