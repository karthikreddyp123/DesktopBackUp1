package com.epam.services;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.epam.models.User;

@FeignClient(name = "user-service")
@Component
@RequestMapping("api/v1/users")
public interface UserServiceClient {

	@GetMapping
	ResponseEntity<List<User>> getUsers();

	@GetMapping("/{id}")
	ResponseEntity<User> getUserById(@PathVariable long id);

	@PostMapping
	ResponseEntity<User> addUser(@RequestBody User user);

	@PutMapping("/{id}")
	ResponseEntity<User> updateUser(@PathVariable long id,@RequestBody User user);

	@DeleteMapping("/{id}")
	ResponseEntity<Void> deleteUser(@PathVariable long id);
}
