package com.projet.BackendPfe.request;

import java.time.LocalDate;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class RegisterRequestGeneraliste {

		private String gender ;
		private String nomPrenom ;
		private String image ; 
		private long telephone ;
		private String reservePassword ; 
	    private String specialite;
		public String getSpecialite() {
		return specialite;
	}
	public void setSpecialite(String specialite) {
		this.specialite = specialite;
	}
		@NotBlank
	    private String username;
	    @NotBlank
	  //  @Size(min=0 , max = 100)
	    @Email
	    private String email;
	  //  @NotBlank
	    //private String role;
	    @NotBlank
	    //@Size( max = 10)
	    private String password;
	    private String role ; 
	    public String getRole() {
			return role;
		}
		public void setRole(String role) {
			this.role = role;
		}
		private LocalDate date_inscription ; 
		public LocalDate getDate_inscription() {
			return date_inscription;
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
		public String getReservePassword() {
			return reservePassword;
		}
		public void setReservePassword(String reservePassword) {
			this.reservePassword = reservePassword;
		}
		
	}

