package com.practice.Ecommerce.Dto;

import lombok.Data;

@Data
public class JwtResponse 
{
    private String token;
    private UserDto dto;
    
}
