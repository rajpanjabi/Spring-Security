package com.SpringSecurity.Securing.Web.Apps.with.Spring.Security.DTOs;

public class RegisterResponseDTO {
	private String message;
	
	public RegisterResponseDTO(String message) {
		this.message = message;
		
	}
	public String getMessage() {
		return this.message;
	}

}

