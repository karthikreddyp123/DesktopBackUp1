package com.epam.controllers;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.epam.models.User;
import com.epam.models.dto.UserDto;
import com.epam.service.UserServiceImplementation;

@RestController
@RequestMapping("api/v1/user")
public class UserController {

	@Autowired
	private UserServiceImplementation userService;
	@PostMapping
	public ResponseEntity<Void> registerUser(@RequestBody UserDto user) {
		User addedUser= userService.addUser(user);
		if(addedUser==null) {
			return ResponseEntity.noContent().build();
		}
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path(
				"/{id}").buildAndExpand(addedUser.getUserId()).toUri();

		return ResponseEntity.created(location).build();
	}
}
