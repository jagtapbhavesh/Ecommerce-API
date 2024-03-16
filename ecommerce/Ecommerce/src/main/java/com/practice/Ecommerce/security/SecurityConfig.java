package com.practice.Ecommerce.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {
	@Autowired
	UserDetailsService detailsService;
	@Autowired
	AuthenticationEntryPoint authenticationEntryPoint;
	@Autowired
	JwtAuthenticationFilter authenticationfilter;
//	@Bean
//	public UserDetailsService detailsService()  {
//		UserDetails U1 = User.withUsername("Bhavesh").password(encoder().encode("1402")).build();
//		UserDetails U2 = User.withUsername("Aakash").password(encoder().encode("0708")).build();
//		return new InMemoryUserDetailsManager(U1,U2);
//	}
	
	@Bean
	public PasswordEncoder encoder() {
		return new BCryptPasswordEncoder();
	}
	
	//csrf
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception{
		httpSecurity
		.csrf(cs->cs.disable())
		.authorizeHttpRequests(req->req.requestMatchers(HttpMethod.POST,"/users")
				.permitAll()
				.requestMatchers(HttpMethod.POST,"/login")
				.permitAll()
				.requestMatchers(HttpMethod.POST,"/product")
				.hasRole("ADMIN")
				.requestMatchers(HttpMethod.DELETE,"/product")
				.hasRole("ADMIN")
				.requestMatchers(HttpMethod.GET, "/users")
				.hasRole("ADMIN")
		.anyRequest().authenticated()
		)
		.exceptionHandling(config->config.authenticationEntryPoint(this.authenticationEntryPoint))
		.sessionManagement(config->config.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        
		httpSecurity.addFilterBefore(authenticationfilter,UsernamePasswordAuthenticationFilter.class);
		return httpSecurity.build();
	}
	
	@Bean
	public DaoAuthenticationProvider authenticationProvider()
	{
	     DaoAuthenticationProvider dao= new DaoAuthenticationProvider();
	     dao.setUserDetailsService(this.detailsService);
	     dao.setPasswordEncoder(encoder());
	     return dao;
	}
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception  
	{
		AuthenticationManager authenticationManager=authenticationConfiguration.getAuthenticationManager();
		return authenticationManager;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}

