package com.SpringSecurity.Securing.Web.Apps.with.Spring.Security.DTOs;

import jakarta.validation.constraints.NotNull;

public class RegisterRequestDTO {
	
	@NotNull(message = "username shouldn't be null")
	private String username;
	
	@NotNull(message = "password shouldn't be null")
	private String password;
	
	public RegisterRequestDTO() {
	}
	
	public RegisterRequestDTO(String username, String password) {
		this.username=username;
		this.password=password;
	}
	
	public String getUsername() {
		return this.username;
	}
	
	public void setUsername(String username) {
		this.username=username;
	}
	public String getPassword() {
	    return password;
	}

	public void setPassword(String password) {
	    this.password = password;
	}
	
	

}
