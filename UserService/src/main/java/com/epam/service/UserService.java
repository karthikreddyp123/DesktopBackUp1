package com.epam.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epam.dto.UserDto;
import com.epam.model.User;
import com.epam.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	public UserDto addUser(UserDto userDto) {
		return modelMapper.map(userRepository.save(modelMapper.map(userDto, User.class)),UserDto.class);
	}
	
	public List<UserDto> getAllUsers(){
		return userRepository.findAll().stream().map(user->modelMapper.map(user, UserDto.class)).collect(Collectors.toList());
	}
	
	public UserDto getUserById(long id) throws UserNotFoundException {
		User user=userRepository.findById(id).orElseThrow(()->new UserNotFoundException("User not found"));
		return modelMapper.map(user, UserDto.class);
	}
	
	public UserDto updateUser(UserDto userDto) throws UserNotFoundException {
		this.getUserById(userDto.getId());
		return modelMapper.map(userRepository.save(modelMapper.map(userDto, User.class)),UserDto.class);
	}
	
	public void deleteUser(long id) throws UserNotFoundException {
		User user=modelMapper.map(this.getUserById(id), User.class);
		userRepository.delete(user);
	}
}
