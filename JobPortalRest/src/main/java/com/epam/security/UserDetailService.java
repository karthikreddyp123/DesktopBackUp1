package com.epam.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.epam.models.User;
import com.epam.repository.UserRepository;
@Service
public class UserDetailService implements UserDetailsService{

	@Autowired
	private UserRepository userRepository;
	@Override
	public UserDetails loadUserByUsername(String username) {
		User user=userRepository.findByUsername(username);
		if(user==null) {
			throw new UsernameNotFoundException("UserNotFound");
		}
		return new UserPrincipal(user);
	}

}
