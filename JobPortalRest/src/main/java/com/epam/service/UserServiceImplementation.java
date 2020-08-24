package com.epam.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.epam.models.User;
import com.epam.models.dto.UserDto;
import com.epam.repository.UserRepository;

@Service
public class UserServiceImplementation {

	@Autowired
	private UserRepository userRepository; 
	@Autowired
	private ModelMapper modelMapper;
	@Autowired
	private PasswordEncoder passwordEncoder;
	public User addUser(UserDto userDto) {
		User user=modelMapper.map(userDto, User.class);
		User checkIfUserExists=userRepository.findByUsername(user.getUsername());
		if(checkIfUserExists==null) {
			user.setPassword(passwordEncoder.encode(userDto.getPassword()));
			return userRepository.save(user);
		}		
		else {
			return null;
		}
	}
}
