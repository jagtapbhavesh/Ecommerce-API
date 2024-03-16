package com.practice.Ecommerce.Entity;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "Userinfo")
public class User implements UserDetails {
	@Id
	//@GeneratedValue(strategy = GenerationType.AUTO)
	private String id;
	private String name;
	private String emailId;
	private String password;
	private String about;
	private String image;
	@JsonManagedReference
	@OneToOne(cascade = CascadeType.ALL)
	private Address address;
	
	@ManyToMany(fetch=FetchType.EAGER,cascade=CascadeType.ALL)
	private Set<Roles> roles= new HashSet<Roles>();
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
       
		return this.roles.stream()
				.map(role->new SimpleGrantedAuthority(role.getRoleName()))
				.collect(Collectors.toSet());
	}
	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return this.emailId;
	}
	
	public String getPassword()
	{
		return this.password;
	}
	
	
	
	
	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}
	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}
	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}
	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}
}
