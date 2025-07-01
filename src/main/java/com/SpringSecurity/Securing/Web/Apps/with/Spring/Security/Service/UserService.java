package com.SpringSecurity.Securing.Web.Apps.with.Spring.Security.Service;
import org.springframework.stereotype.Service;
import com.SpringSecurity.Securing.Web.Apps.with.Spring.Security.DTOs.RegisterRequestDTO;
import com.SpringSecurity.Securing.Web.Apps.with.Spring.Security.DTOs.RegisterResponseDTO;
import com.SpringSecurity.Securing.Web.Apps.with.Spring.Security.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.SpringSecurity.Securing.Web.Apps.with.Spring.Security.Entity.Role;
import com.SpringSecurity.Securing.Web.Apps.with.Spring.Security.Entity.User;

import java.util.List;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService{
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	public RegisterResponseDTO registerUser(RegisterRequestDTO request) {
		Optional<User> existingUser =userRepository.findByUsername(request.getUsername());
		if (existingUser.isPresent()) {
			return new RegisterResponseDTO("Username already taken");
		}
//		else we hash the password using passwordEncoder.encode and save it to db and return success message
		String encodedPassword=passwordEncoder.encode(request.getPassword());
		User newUser= new User(request.getUsername(),encodedPassword, Role.USER);
		userRepository.save(newUser);
		return new RegisterResponseDTO("New user registered successfully");
		
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		User user=userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User not found"));
		return new org.springframework.security.core.userdetails.User(
	            user.getUsername(),
	            user.getPassword(),
	            List.of(new SimpleGrantedAuthority("ROLE_" + user.getRole().name()))
	    );
    }
	}
		
		
	
	
	

	
	


