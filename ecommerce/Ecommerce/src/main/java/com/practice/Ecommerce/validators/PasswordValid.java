package com.practice.Ecommerce.validators;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Constraint(validatedBy =PasswordValidator.class )
public @interface PasswordValid {
	
	String message() default "Password must be more than 6 characters";

	Class<?>[] groups() default { };

	Class<? extends Payload>[] payload() default { };

}
