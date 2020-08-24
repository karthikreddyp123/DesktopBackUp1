package com.epam.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.epam.models.User;
import com.epam.repository.UserRepository;

@Component
public class CurrentSession {
	@Autowired
	private UserRepository userRepo;
	private User user;
	protected User getCurrentUser() {
		Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
		if (user==null||!(loggedInUser.getName().equalsIgnoreCase(user.getUsername()))) {
			user = userRepo.findByUsername(loggedInUser.getName());
		}
		return user;
	}
}
