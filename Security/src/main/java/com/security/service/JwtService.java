package com.security.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.security.config.CustomUserDetailsService;
import com.security.entity.UserCredential;
import com.security.repository.UserCredentialRepository;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

@Component
public class JwtService {


    public static final String SECRET = "5367566B59703373367639792F423F4528482B4D6251655468576D5A71347437";

    @Autowired
    private CustomUserDetailsService customUserDetailsService;
    
    @Autowired
    private UserCredentialRepository userCredentialRepository;

    public boolean validateToken(final String token) {
        Jwts.parserBuilder().setSigningKey(getSignKey()).build().parseClaimsJws(token);
        return extractClaim(token, Claims::getExpiration).before(new Date());
    }


    public String extractUsername(String token) {
		return extractClaim(token, Claims::getSubject);
	}
    
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
		final Jws<Claims> claimsJws = Jwts.parserBuilder().setSigningKey(getSignKey()).build().parseClaimsJws(token);
		return claimsResolver.apply(claimsJws.getBody());
	}
    
    public String generateToken(String userName) {
        Map<String, Object> claims = new HashMap<>();
        return createToken(claims, userName);
    }

    public String getRoleFromToken(String token) {
		Claims claims = extractAllClaims(token);
		return claims.get("role", String.class);
	}
    
    private Claims extractAllClaims(String token) {
		return Jwts.parserBuilder().setSigningKey(getSignKey()).build().parseClaimsJws(token).getBody();
	}
    
    private String createToken(Map<String, Object> claims, String userName) {
    	Optional<UserCredential> user= userCredentialRepository.findByEmail(userName);
    	claims.put("role", user.get().getRole());
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(userName)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 30))
                .signWith(getSignKey(), SignatureAlgorithm.HS256).compact();
    }

    private Key getSignKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
