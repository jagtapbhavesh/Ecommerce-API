package com.practice.Ecommerce.Controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.practice.Ecommerce.Dto.UserDto;
import com.practice.Ecommerce.service.FileService;
import com.practice.Ecommerce.service.UserService;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/users")


public class UserController {
	
	@Value("${user.image}")
	String imagePath;

	@Autowired
	UserService service;
	
	@Autowired
	FileService fileService;
	
	@PostMapping
	public ResponseEntity<UserDto> addUser(@Valid @RequestBody UserDto dto)
	{
		
		UserDto savedUser = service.createUser(dto);
		return new ResponseEntity<UserDto>(savedUser, HttpStatus.CREATED);	
	}
	
	
	@PutMapping("/{id}")
	public ResponseEntity<UserDto> UpdateUser
	(@PathVariable String id,@RequestBody UserDto dto)
	{
		
		UserDto updateUser = service.updateUser(id, dto);
		return new ResponseEntity<UserDto>(updateUser, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<UserDto> getUser(@PathVariable String id)
	{
		UserDto dto = service.getById(id);
		return new ResponseEntity<UserDto>(dto, HttpStatus.CREATED);
	}
	
	@GetMapping
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<List<UserDto>> getAll()
	{
		List<UserDto> allUsers = service.getAllUsers();
		return new ResponseEntity<List<UserDto>>(allUsers, HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> DeleteUser(@PathVariable String id)
	{
		service.deleteUser(id);
		return new ResponseEntity<String>(id+" deletted ", HttpStatus.GONE);
	}
	
	@GetMapping("/email")
	public ResponseEntity<UserDto> getByEmail(@RequestParam String email)
	{
		UserDto Dto = service.getByEmail(email);
		return new ResponseEntity<UserDto>(Dto, HttpStatus.FOUND);
		
	}
	
	
	@GetMapping("/keyword/{key}")
	public ResponseEntity<List<UserDto>> getByKeyword(@PathVariable("key") String  keyword)
	{
		
		List<UserDto> userDto = service.getByKeyword(keyword);
		return new ResponseEntity<List<UserDto>>(userDto, HttpStatus.FOUND);
	}
	
	@PostMapping("/image/{id}")
	public ResponseEntity<String>uploadImage(@RequestParam MultipartFile image,@PathVariable String id)
	{
		String uploadImage = fileService.uploadsImage(image, imagePath);
		UserDto user= service.getById(id);
		user.setImage(uploadImage);
		service.updateUser(id, user);
		
		
		return new ResponseEntity<String>(uploadImage,HttpStatus.OK);
	}
	@GetMapping("/image/{id}")
	public void serveImage(@PathVariable String id,HttpServletResponse response) throws IOException
	{
		UserDto user = service.getById(id);
		String image=user.getImage();
		InputStream inputstream= fileService.getImage(imagePath,image);
		response.setContentType(MediaType.IMAGE_JPEG_VALUE);
		StreamUtils.copy(inputstream, response.getOutputStream());
	}
	

}
