package com.epam.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.epam.repository.UserRepository;
import com.epam.security.filter.JwtAuthenticationFilter;
import com.epam.security.filter.JwtAuthorizationFilter;

@Configuration
@EnableWebSecurity
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter{
	@Autowired
	private UserDetailService userDetailService;
	@Autowired
	private UserRepository userRepository;
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth
		.authenticationProvider(authenticationProvider());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
		.csrf().disable()
		.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
		.and()
		.addFilter(new JwtAuthenticationFilter(authenticationManager()))
		.addFilter(new JwtAuthorizationFilter(authenticationManager(),userRepository))
		.authorizeRequests()
		.antMatchers("/login").permitAll()
		.antMatchers("/api/v1/user").permitAll()
		.antMatchers("/jobprovider/**").hasRole("JOBPROVIDER")
		.antMatchers("/jobseeker/**").hasRole("JOBSEEKER");
	}

	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider provider=new DaoAuthenticationProvider();
		provider.setPasswordEncoder(passwordEncoder());
		provider.setUserDetailsService(userDetailService);
		return provider;
	}

}
