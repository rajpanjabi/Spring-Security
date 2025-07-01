package com.SpringSecurity.Securing.Web.Apps.with.Spring.Security.Service;
import com.SpringSecurity.Securing.Web.Apps.with.Spring.Security.DTOs.LoginRequestDTO;
import com.SpringSecurity.Securing.Web.Apps.with.Spring.Security.DTOs.LoginResponseDTO;
import com.SpringSecurity.Securing.Web.Apps.with.Spring.Security.Repository.UserRepository;

import com.SpringSecurity.Securing.Web.Apps.with.Spring.Security.Entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired 
	private JWTService jwtService;
	
	@Autowired
    private AuthenticationManager authenticationManager;
	
	public LoginResponseDTO login(LoginRequestDTO request) {
		
        // Authenticate the credentials using Spring Security
		authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
		
		// If authentication is successful, generate JWT
		User user =userRepository.findByUsername(request.getUsername()).orElseThrow(()-> new RuntimeException("User not found"));
		
//		now generate a token using jwt service
		String token = jwtService.generateToken(user);
		
		return new LoginResponseDTO("Login successful", token);
	}

}
