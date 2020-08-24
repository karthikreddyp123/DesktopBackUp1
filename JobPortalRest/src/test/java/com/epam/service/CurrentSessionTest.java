package com.epam.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;

import com.epam.models.User;
import com.epam.repository.UserRepository;

@SpringBootTest(classes = CurrentSession.class)
@TestMethodOrder(OrderAnnotation.class)
@WithMockUser(username = "Karthik@epam.com", roles = "JOBPROVIDER")
class CurrentSessionTest {

	@MockBean
	private UserRepository userRepository;
	@Autowired
	@InjectMocks
	private CurrentSession currentSession;
	@Test
	@Order(1)
	void getCurrentUserReturnsUser() {
		User user=new User("Test", "Test", "Test", "Test", 1);
		Mockito.doReturn(user).when(userRepository).findByUsername(Mockito.anyString());
		assertEquals(user.getUsername(), currentSession.getCurrentUser().getUsername());
	}
	@Test
	@Order(2)
	void getCurrentUserReturnsUserTest() {
		User user=new User("Test", "Test", "Karthik@epam.com", "Test", 1);
		Mockito.doReturn(user).when(userRepository).findByUsername(Mockito.anyString());
		assertEquals(user.getUsername(), currentSession.getCurrentUser().getUsername());
	}
	@Test
	@Order(3)
	void getCurrentUserReturnsUserTest2() {
		User user=new User("Test", "Test", "Karthik@epam.com", "Test", 1);
		Mockito.doReturn(user).when(userRepository).findByUsername(Mockito.anyString());
		assertEquals(user.getUsername(), currentSession.getCurrentUser().getUsername());
	}
}
