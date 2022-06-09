package com.projet.BackendPfe.services;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.projet.BackendPfe.Entity.AdminDigitalManager;
import com.projet.BackendPfe.Entity.Expert;
import com.projet.BackendPfe.repository.AdminDigitalMangerRepository;
import com.projet.BackendPfe.repository.ExpertRepository;

@Service
public class AdminDigitalManagerService implements IAdminDigitalManager{

	@Autowired  AdminDigitalMangerRepository   repository ;
	
	
	@Override
	public byte[] getImageAdminDigitalManager(long id) throws Exception {
		String imageAdmin =repository.findById(id).get().getImage() ; 
		Path p =Paths.get(System.getProperty("user.home")+"/files/",imageAdmin);
		return Files.readAllBytes(p);
	}

	@Override
	public void updateImageAdminDigitalManager(long id, MultipartFile file) throws IOException {
		 AdminDigitalManager  admin = repository.findById(id).get();
		 StringBuilder fileNames=new StringBuilder();
		 Path fileNameAndPath=Paths.get(System.getProperty("user.home")+"/files/",file.getOriginalFilename()+"");
		 fileNames.append(file);
		 System.out.println("bagraa"+fileNameAndPath);
		 Files.write(fileNameAndPath, file.getBytes());
		 admin.setImage(file.getOriginalFilename());
		 repository.save(admin);
		
	}
	
}
