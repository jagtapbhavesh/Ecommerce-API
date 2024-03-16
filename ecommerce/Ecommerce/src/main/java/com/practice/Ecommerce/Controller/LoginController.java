


package com.practice.Ecommerce.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.practice.Ecommerce.Dto.JwtRequest;
import com.practice.Ecommerce.Dto.JwtResponse;
import com.practice.Ecommerce.Dto.UserDto;
import com.practice.Ecommerce.security.JwtHelper;
import com.practice.Ecommerce.service.CustomerDetailsService;
import com.practice.Ecommerce.service.impl.UserServiceImpl;


@RestController
@RequestMapping("/login")
public class LoginController {
	@Autowired
	UserServiceImpl userService;
	@Autowired
	JwtHelper helper;
	@Autowired
	CustomerDetailsService detailsService;
	@Autowired
	AuthenticationManager authenticationManager;
    public void doAuthenticate(String email, String password)
    {
    	UsernamePasswordAuthenticationToken AuthenticationToken  = new UsernamePasswordAuthenticationToken(email,password);
    	
    	authenticationManager.authenticate(AuthenticationToken);
    	
    }
    
    @PostMapping
    public ResponseEntity<JwtResponse> login(@RequestBody JwtRequest jwtRequest)
    {   
    	this.doAuthenticate(jwtRequest.getEmailId(), jwtRequest.getPassword());
    	
    	UserDetails u= this.detailsService.loadUserByUsername(jwtRequest.getEmailId());
    	
    	String email = u.getUsername();
    	
    	UserDto dto = this.userService.getByEmail(email);
    	
    	String token= this.helper.generateToken(u);
    	
    	JwtResponse response=new JwtResponse();
    	
    	response.setToken(token);
    	
    	response.setDto(dto);
    	return new ResponseEntity<JwtResponse>(response,HttpStatus.OK);
    }
    
}
