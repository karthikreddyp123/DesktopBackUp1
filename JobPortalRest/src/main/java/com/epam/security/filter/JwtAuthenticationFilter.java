package com.epam.security.filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.epam.models.dto.LoginDto;
import com.epam.security.JwtProperties;
import com.epam.security.UserPrincipal;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter{
	private AuthenticationManager authenticationManager;
	public JwtAuthenticationFilter(AuthenticationManager authenticationManager) {
		this.authenticationManager = authenticationManager;
	}
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
	{
		LoginDto creds=null;
		try {
			creds=new ObjectMapper().readValue(request.getInputStream(), LoginDto.class);
		} catch (IOException e) {
			e.printStackTrace();
		}
		UsernamePasswordAuthenticationToken token=null;
		if(creds!=null) {
		token=new UsernamePasswordAuthenticationToken(creds.getUsername(), creds.getPassword(), new ArrayList<>());
		}
		return authenticationManager.authenticate(token);
	}

	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {
		UserPrincipal principal=(UserPrincipal) authResult.getPrincipal();
		String token=JWT
				.create()
				.withSubject(principal.getUsername())
				.withExpiresAt(new Date(System.currentTimeMillis()+JwtProperties.EXPIRATION_TIME))
				.sign(Algorithm.HMAC512(JwtProperties.SECRET));
		response.addHeader(JwtProperties.HEADER_STRING, JwtProperties.TOKEN_PREFIX+token);
	}

}
