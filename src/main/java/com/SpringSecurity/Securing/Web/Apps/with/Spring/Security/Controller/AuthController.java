package com.SpringSecurity.Securing.Web.Apps.with.Spring.Security.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.SpringSecurity.Securing.Web.Apps.with.Spring.Security.DTOs.LoginRequestDTO;
import com.SpringSecurity.Securing.Web.Apps.with.Spring.Security.DTOs.LoginResponseDTO;
import com.SpringSecurity.Securing.Web.Apps.with.Spring.Security.DTOs.RegisterRequestDTO;
import com.SpringSecurity.Securing.Web.Apps.with.Spring.Security.DTOs.RegisterResponseDTO;
import com.SpringSecurity.Securing.Web.Apps.with.Spring.Security.Service.AuthService;
import com.SpringSecurity.Securing.Web.Apps.with.Spring.Security.Service.UserService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private AuthService authService;
	
	@PostMapping("/register")
	public ResponseEntity<RegisterResponseDTO> register(@RequestBody RegisterRequestDTO request){
		RegisterResponseDTO response =userService.registerUser(request);
		return ResponseEntity.ok(response);
		
		
	}
	
	@PostMapping("/login")
	public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginRequestDTO request){
		LoginResponseDTO response=authService.login(request);
		return ResponseEntity.ok(response);
	}

}
