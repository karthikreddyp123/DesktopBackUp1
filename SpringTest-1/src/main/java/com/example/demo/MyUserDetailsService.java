package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.Models.Users;
import com.example.demo.repo.UserRepo;
@Service
public class MyUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepo userRepo;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Users user=userRepo.findByUserName(username);
		if(user==null) {
			throw new UsernameNotFoundException("User Not Found");
		}
		return new UserCurrent(user);
	}

}
