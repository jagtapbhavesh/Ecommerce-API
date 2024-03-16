package com.practice.Ecommerce.service;

import java.io.InputStream;

import org.springframework.web.multipart.MultipartFile;

public interface FileService {
	
	String uploadsImage(MultipartFile file,String path);
	InputStream getImage(String path,String name);

}
