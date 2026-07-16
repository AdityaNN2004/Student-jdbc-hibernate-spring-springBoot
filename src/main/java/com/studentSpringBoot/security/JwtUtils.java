package com.studentSpringBoot.security;

import java.util.Date;

import javax.crypto.SecretKey;

//import java.security.Key;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtils {
	private final SecretKey key = Jwts.SIG.HS256.key().build();
	private final int jwtExpirationMs = 86400000;

	public String generateJwt(String email) {
		return Jwts.builder()
				.subject(email)
				.issuedAt(new Date())
				.expiration(new Date((new Date()).getTime() + jwtExpirationMs))
				.signWith(key)
				.compact();
	}
	
	public String getEmailFromJwt(String token) {
		return Jwts.parser()
				.verifyWith(key)
				.build()
				.parseSignedClaims(token)
				.getPayload()
				.getSubject();
	}
	
	public boolean validateToken(String token) {
		try {
			 Jwts.parser()
					.verifyWith(key)
					.build()
					.parseSignedClaims(token);
			 return true;
		}catch(Exception e) {
			return false;
		}
	}
}
