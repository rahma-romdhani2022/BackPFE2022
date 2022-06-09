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

import com.projet.BackendPfe.Entity.Consultation;
import com.projet.BackendPfe.Entity.Expert;
import com.projet.BackendPfe.repository.ExpertRepository;

@Service
public class ExpertService implements IExpert{
	protected String gender ;
	protected long telephone;

	
	@Autowired
	ExpertRepository expertRepository;
	
/******* get Image Profile docteur ***********/
	@Override
	public byte[] getImageExpert(long  id) throws Exception{
		String imageExpert =expertRepository.findById(id).get().getImage() ; 
		Path p =Paths.get(System.getProperty("user.home")+"/files/",imageExpert);
		return Files.readAllBytes(p);
		
	}
	@Override
	public void updateImageExpert(long id, MultipartFile file) throws IOException {
			
			 Expert  expert = expertRepository.findById(id).get();
			 StringBuilder fileNames=new StringBuilder();
			 Path fileNameAndPath=Paths.get(System.getProperty("user.home")+"/files/",file.getOriginalFilename()+"");
			 fileNames.append(file);
			 System.out.println("bagraa"+fileNameAndPath);
			 Files.write(fileNameAndPath, file.getBytes());
			 expert.setImage(file.getOriginalFilename());
			 
			 expertRepository.save(expert);
		} 
		
	
	/******************************
	public Expert updateImage(long id , MultipartFile file) throws IOException {
		 Expert expert = expertRepository.findById(id).get();
		expert.setImage(compressZLib(file.getBytes()));
		expertRepository.save(expert);
		return expert ; 
	}*/
	public void ajouterExpert( Expert  expert) throws IOException {
		 Expert expert1 = new Expert();
		 expert1.setEmail(expert.getEmail());
		 expert1.setUsername(expert.getUsername());
		 expert1.setGender(expert.getGender());
		 expert1.setTelephone(expert.getTelephone());
		 expert1.setPassword(expert.getPassword());
		// expert1.setImage(compressZLib(expert.getImage()));
		expert1.setAdmin(expert.getAdmin());
		expertRepository.save(expert1);
	}
	public static byte[] compressZLib(byte[] data) {
		Deflater deflater = new Deflater();
		deflater.setInput(data);
		deflater.finish();

		ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
		byte[] buffer = new byte[1024];
		while (!deflater.finished()) {
			int count = deflater.deflate(buffer);
			outputStream.write(buffer, 0, count);
		}
		try {
			outputStream.close();
		} catch (IOException e) {
		}
		System.out.println("Compressed Image Byte Size - " + outputStream.toByteArray().length);

		return outputStream.toByteArray();
	}

	

}