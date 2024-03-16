package com.practice.Ecommerce.security;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.practice.Ecommerce.service.CustomerDetailsService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter{
     
	@Autowired
	JwtHelper helper;
	
	@Autowired
	CustomerDetailsService detailService;

	
	
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		String header = request.getHeader("Authorization");
		System.out.println(header);
		String username=null;
		String token=null;
		if(header!=null && header.startsWith("Bearer"))
		{
			 token = header.substring(7);
			 System.out.println(token);
			 username= this.helper.getUsernameFromToken(token);
			 System.out.println(username);
			 
			 
		}
		
		if(username!=null && SecurityContextHolder.getContext().getAuthentication()==null)
		{
			UserDetails u = this.detailService.loadUserByUsername(username);
			Boolean validateToken=this.helper.validateToken(token, u);
			
			if(validateToken)
			{
				UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken( u, null,u.getAuthorities());
				authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(authenticationToken);
				System.out.println("Authorization sucessfullllll");
			}
			else
			{
				System.out.println("Authorization failed");
			}
			
			
			
		}
		
		filterChain.doFilter(request, response);
		
		
		
		
		
	}
      
}
