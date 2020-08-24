package com.epam.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.epam.models.User;
import com.epam.services.UserServiceClient;

@RestController
@RequestMapping("library/users")
public class LibraryUserController {
	@Autowired
	private UserServiceClient userServiceClient;
	
	@GetMapping
	public ResponseEntity<List<User>> getUsers(){
		
		return userServiceClient.getUsers();
	}
	@GetMapping("/{id}")
	public ResponseEntity<User> getUserById(@PathVariable int id) {
		ResponseEntity<User> response=userServiceClient.getUserById(id);
		System.out.println(response.getStatusCode());
		if(response.getStatusCode()==HttpStatus.BAD_REQUEST) {
			return  ResponseEntity.badRequest().build();
		}
		return response;
	}
	@PostMapping
	public ResponseEntity<User> addUser(@RequestBody User user){
		return userServiceClient.addUser(user);		
	}
	@PutMapping("/{id}")
	public ResponseEntity<User> updateUser(@PathVariable int id,@RequestBody User user){
		return userServiceClient.updateUser(id, user);
	}
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteUser(@PathVariable int id){
		ResponseEntity<Void> response=userServiceClient.deleteUser(id);
		if(response.getStatusCode()==HttpStatus.BAD_REQUEST) {
			return  ResponseEntity.badRequest().build();
		}
		return response;
	}
}
