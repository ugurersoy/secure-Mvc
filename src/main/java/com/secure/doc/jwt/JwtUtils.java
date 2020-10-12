package com.secure.doc.jwt;

import java.util.Date;


import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.secure.doc.services.UserDetailsImpl;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

@Component
public class JwtUtils {
	
	

	private String jwtSecret="asdasdadasdasdadasdad";

	
	private int jwtExpirationMs=86400000;

	public String generateJwtToken(Authentication authentication) {

		UserDetailsImpl userPrincipal = (UserDetailsImpl) authentication.getPrincipal();

		return Jwts.builder()
				.setSubject((userPrincipal.getUsername()))
				.setIssuedAt(new Date())// oluşturma tarihi 
				.setExpiration(new Date((new Date()).getTime() + jwtExpirationMs)) //sure 
				.signWith(SignatureAlgorithm.HS512, jwtSecret)
				.compact();
	}

	public String getUserNameFromJwtToken(String token) {
		return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody().getSubject();
	}

	public boolean validateJwtToken(String authToken) {
		try {
			Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
			return true;
		} catch (SignatureException e) {
			System.out.println("Invalid JWT signature: {}"+e.getMessage());
		} catch (MalformedJwtException e) {
			System.out.println("Invalid JWT token: {}"+e.getMessage());
		} catch (ExpiredJwtException e) {
			System.out.println("JWT token is expired: {}"+ e.getMessage());
		} catch (UnsupportedJwtException e) {
			System.out.println("JWT token is unsupported: {}"+ e.getMessage());
		} catch (IllegalArgumentException e) {
			System.out.println("JWT claims string is empty: {}"+e.getMessage());
		}

		return false;
	}

}
