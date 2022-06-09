package com.projet.BackendPfe.services;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

public interface IConsultation {

	/************ Get des images droite ***************/
	public byte[] getImageDroite1(long  id) throws Exception ; 
	public byte[] getImageDroite2(long  id) throws Exception ; 
	public byte[] getImageDroite3(long  id) throws Exception ; 
	public byte[] getImageDroite4(long  id) throws Exception ; 
	public byte[] getImageDroite5(long  id) throws Exception ; 
	

	/************ Get des images  gauche***************/
	public byte[] getImageGauche1(long  id) throws Exception ; 
	public byte[] getImageGauche2(long  id) throws Exception ; 
	public byte[] getImageGauche3(long  id) throws Exception ; 
	public byte[] getImageGauche4(long  id) throws Exception ; 
	public byte[] getImageGauche5(long  id) throws Exception ; 
	
	
	
	/*****************Put les images droite ******************/
	
	public void updateImageDroite1(long id , MultipartFile file) throws IOException;
	public void updateImageDroite2(long id , MultipartFile file) throws IOException;
	public void updateImageDroite3(long id , MultipartFile file) throws IOException;
	public void updateImageDroite4(long id , MultipartFile file) throws IOException;
	public void updateImageDroite5(long id , MultipartFile file) throws IOException;
	
	/*****************Put les images gauche ******************/
	
	public void updateImageGauche1(long id , MultipartFile file) throws IOException;
	public void updateImageGauche2(long id , MultipartFile file) throws IOException;
	public void updateImageGauche3(long id , MultipartFile file) throws IOException;
	public void updateImageGauche4(long id , MultipartFile file) throws IOException;
	public void updateImageGauche5(long id , MultipartFile file) throws IOException;
}
