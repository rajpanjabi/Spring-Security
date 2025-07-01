package com.SpringSecurity.Securing.Web.Apps.with.Spring.Security.DTOs;
import jakarta.validation.constraints.NotNull;

public class LoginRequestDTO {
	
	@NotNull(message = "username must not be null")
    private String username;

    @NotNull(message = "password must not be null")
    private String password;
    
    public LoginRequestDTO() {}

    public LoginRequestDTO(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}


