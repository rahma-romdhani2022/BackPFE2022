package com.projet.BackendPfe.services;

import java.io.IOException;
import org.springframework.web.multipart.MultipartFile;

public interface IAdminMedicalManager {
public byte[] getImageAdminMedicalManager(long  id) throws Exception;
	
	public void updateImageAdminMedicalManager(long id , MultipartFile file) throws IOException;
}