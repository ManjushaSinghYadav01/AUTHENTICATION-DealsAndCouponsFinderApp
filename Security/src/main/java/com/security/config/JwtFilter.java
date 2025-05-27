package com.security.config;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.security.service.JwtService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtFilter extends OncePerRequestFilter{
	
	@Autowired
	private JwtService jwtService;
	
		@Override
		protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
				FilterChain filterChain) throws ServletException, IOException {
			// TODO Auto-generated method stub
			try {
			String authHeader = request.getHeader("Authorization");
			String token = null;
			String username = null;
			if (authHeader != null && authHeader.startsWith("Bearer ")) {
				jwtService=new JwtService();
				token = authHeader.substring(7);
				username = jwtService.extractUsername(token);
			}
			if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
				List<SimpleGrantedAuthority> authorities = Arrays.asList(jwtService.getRoleFromToken(token))
						.stream()
						.map(role->role.startsWith("ROLE_") ? role : "ROLE_" + role)
						.map(SimpleGrantedAuthority::new)
						.toList();
				
					if(!jwtService.validateToken(token)) {
						UsernamePasswordAuthenticationToken autkToken = 
								new UsernamePasswordAuthenticationToken(username, null, authorities);
						autkToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
						SecurityContextHolder.getContext().setAuthentication(autkToken);
					}
				
			}
			
			filterChain.doFilter(request, response);
			}catch (Exception e) {
				System.out.println("Error in JwtFilter: " + e.getMessage());
				e.printStackTrace();
			}
		}

}
