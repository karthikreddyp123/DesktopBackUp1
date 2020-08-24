package com.epam.service;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.epam.models.User;
import com.epam.models.dto.UserDto;
import com.epam.repository.UserRepository;
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
class UserServiceImplementationTest {

	@MockBean
	private UserRepository userRepository;
	@Autowired
	@InjectMocks
	private UserServiceImplementation userService;
	@BeforeEach
	void initialize() {
		MockitoAnnotations.initMocks(this);
	}
	@Test
	void addUserReturnsUser() {
		UserDto userDto=new UserDto(1,"Test","Test","Test",1);
		userDto.setPassword("Test");
		User user=new User("Test", "Test", "Test", "Test", 1);
		Mockito.doReturn(null).when(userRepository).findByUsername(Mockito.anyString());
		Mockito.doReturn(user).when(userRepository).save(Mockito.any(User.class));
		assertEquals("Test", userService.addUser(userDto).getUsername());
	}
	@Test
	void addUserReturnsNull() {
		UserDto userDto=new UserDto(1,"Test","Test","Test",1);
		User user=new User("Test", "Test", "Test", "Test", 1);
		Mockito.doReturn(user).when(userRepository).findByUsername(Mockito.anyString());
		Mockito.doReturn(user).when(userRepository).save(Mockito.any(User.class));
		assertEquals(null, userService.addUser(userDto));
	}
}
