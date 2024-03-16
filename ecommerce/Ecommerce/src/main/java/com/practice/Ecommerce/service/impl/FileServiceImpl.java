package com.practice.Ecommerce.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.practice.Ecommerce.service.FileService;
@Service
public class FileServiceImpl implements FileService{

	@Override
	public String uploadsImage(MultipartFile file, String path) {
		//to get the name of file:::: abc.png
		String fileName = file.getOriginalFilename();
		String extension = fileName.substring(fileName.lastIndexOf('.'));
		String files = UUID.randomUUID().toString();
		String newFileName=files+extension;
		//images/users/
		String FileNameWithPath=path+File.separator+newFileName;
		File folder=new File(path);
		if(!folder.exists())
		{
			folder.mkdirs();
		}
		try {
			Files.copy(file.getInputStream(), Paths.get(FileNameWithPath));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		return newFileName;
		
	}

	@Override
	public InputStream getImage(String path, String name) {
		String fullPath=path+File.separator+name;
		FileInputStream fis=null;
		try {
			fis=new FileInputStream(fullPath);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return fis;
	}
	

}
