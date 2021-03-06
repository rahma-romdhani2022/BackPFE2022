package com.projet.BackendPfe.request;

import java.time.LocalDate;

import javax.persistence.ManyToOne;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.projet.BackendPfe.Entity.AdminMedicalManager;

public class RegisterRequestExpert {
	private String gender ;
	private String nomPrenom ;
	private String image ; 
	private long telephone ; 
	private String reserve ;  
	@NotBlank
    private String username;
    @NotBlank
   // @Size(min=0 , max = 100)
    @Email
    private String email;
  //  @NotBlank
   // private String role;
    @NotBlank
    //@Size( max = 10)
    private String password;
    private LocalDate date_inscription ; 
    private String role ; 
  	public LocalDate getDate_inscription() {
  		return date_inscription;
  	}
    public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	@ManyToOne
	private AdminMedicalManager admin;
	public AdminMedicalManager getAdmin() {
		return admin;
	}
	public void setAdmin(AdminMedicalManager admin) {
		this.admin = admin;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
/*	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}*/
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getGender() {
		return gender;
	} 
	public String getImage() {
		return image;
	} 
	public long getTelephone() {
		return telephone;
	} 
	public void setGender(String gender) {
		this.gender = gender;
	}public void setImage(String image) {
		this.image = image;
	}public void setTelephone(long telephone) {
		this.telephone = telephone;
	}
	public String getNomPrenom() {
		return nomPrenom;
	}
	public void setNomPrenom(String nomPrenom) {
		this.nomPrenom = nomPrenom;
	}
	public String getReserve() {
		return reserve;
	}
	public void setReserve(String reserve) {
		this.reserve = reserve;
	}
	
}


