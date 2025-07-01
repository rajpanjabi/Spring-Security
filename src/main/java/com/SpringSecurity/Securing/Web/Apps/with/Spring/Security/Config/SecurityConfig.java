package com.SpringSecurity.Securing.Web.Apps.with.Spring.Security.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
	
	 private final AuthenticationConfiguration authenticationConfiguration;
	 
	 @Bean
	    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
	        http
	            .csrf(csrf -> csrf.disable())
	            .authorizeHttpRequests(auth -> auth
	                .requestMatchers("/api/auth/**").permitAll() // ✅ Allow public access
	                .anyRequest().authenticated() // 🔒 Protect other routes
	            )
	            .sessionManagement(session -> session
	                .sessionCreationPolicy(SessionCreationPolicy.STATELESS) // ⛔ No sessions, use JWT
	            )
	            .formLogin(login -> login.disable()); // 🚫 No default login form

	        return http.build();
	    }

	    public SecurityConfig(AuthenticationConfiguration authenticationConfiguration) {
	        this.authenticationConfiguration = authenticationConfiguration;
	    }

	    @Bean
	    public AuthenticationManager authenticationManager() throws Exception {
	        // This fetches the default AuthenticationManager built by Spring Security
	        return authenticationConfiguration.getAuthenticationManager();
	    }

	
	
	@Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}




