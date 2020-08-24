package com.epam.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.epam.dto.UserDto;
import com.epam.service.UserNotFoundException;
import com.epam.service.UserService;

@RestController
@RequestMapping("api/v1/users")
public class UserController {

	@Autowired
	private UserService userService;
	@GetMapping
	public ResponseEntity<List<UserDto>> getAllUsers(){
		List<UserDto> users = userService.getAllUsers();
		return ResponseEntity.ok(users);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<UserDto> getUser(@PathVariable long id){
		UserDto userDto;
		try {
			userDto = userService.getUserById(id);
			return ResponseEntity.ok(userDto);
		} catch (UserNotFoundException e) {
			return ResponseEntity.badRequest().build();
		}	
	}
	
	@PostMapping
	public ResponseEntity<UserDto> addUser(@RequestBody UserDto userDto){
		UserDto user=userService.addUser(userDto);
		URI location=ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(user.getId()).toUri();
		return ResponseEntity.created(location).body(user);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<UserDto> updateUser(@PathVariable long id,@RequestBody UserDto userDto){
		UserDto user;
		try {
			user = userService.updateUser(userDto);
			URI location=ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(user.getId()).toUri();
			return ResponseEntity.created(location).body(user);
		} catch (UserNotFoundException e) {
			return ResponseEntity.badRequest().build();
		}	
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteUser(@PathVariable long id){
		try {
			userService.deleteUser(id);
			return ResponseEntity.noContent().build();
		} catch (UserNotFoundException e) {
			return ResponseEntity.badRequest().build();
		}
	}
	
	
}
