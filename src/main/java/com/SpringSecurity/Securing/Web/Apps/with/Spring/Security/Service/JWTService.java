package com.SpringSecurity.Securing.Web.Apps.with.Spring.Security.Service;



import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.SpringSecurity.Securing.Web.Apps.with.Spring.Security.Entity.User;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import java.security.Key;
import java.util.Date;
import java.util.function.Function;


@Service
public class JWTService {
	
	@Value("${jwt.secret}")
	private String secret;
	
	@Value("${jwt.expiration}")
	private long expirationMillis;
	
	private Key getSigningKey() {
	    return Keys.hmacShaKeyFor(secret.getBytes());
	}
	
	public String generateToken(User user) {
	    return Jwts.builder()
	            .setSubject(user.getUsername()) // this will be available as "sub" claim
	            .claim("role", user.getRole().name()) // custom claim
	            .setIssuedAt(new Date(System.currentTimeMillis())) // token creation time
	            .setExpiration(new Date(System.currentTimeMillis() + expirationMillis)) // expiration
	            .signWith(getSigningKey(), SignatureAlgorithm.HS256) // sign the token
	            .compact(); // return the full token string
	}
	
	public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    public boolean isTokenValid(String token, User user) {
        final String username = extractUsername(token);
        return username.equals(user.getUsername()) && !isTokenExpired(token);
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}



