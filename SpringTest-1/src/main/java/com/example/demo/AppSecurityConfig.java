package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;

@Configuration
@EnableWebSecurity
public class AppSecurityConfig extends WebSecurityConfigurerAdapter{
//	@Bean
//	@Override
//	protected UserDetailsService userDetailsService() {
//		List<UserDetails> users = new ArrayList<>();
//		users.add(User.withDefaultPasswordEncoder().username("Karthik").password("1234").roles("USER").build());
//		return new InMemoryUserDetailsManager(users);
//	}
	@Autowired
	private UserDetailsService userDetailsService;
	@Bean
	public AuthenticationProvider getAuthProvider() {
		DaoAuthenticationProvider authenticationProvider=new DaoAuthenticationProvider();
		authenticationProvider.setUserDetailsService(userDetailsService);
		authenticationProvider.setPasswordEncoder(NoOpPasswordEncoder.getInstance());
		return authenticationProvider;
	}
    @Override
    protected void configure(final HttpSecurity http) throws Exception {
        http
          .csrf().disable()
          
          .formLogin()
          .loginPage("/login")
          .defaultSuccessUrl("/home", true)
          .and()
          .logout()
          .logoutSuccessUrl("/login")
          .deleteCookies("JSESSIONID");
    }
}
