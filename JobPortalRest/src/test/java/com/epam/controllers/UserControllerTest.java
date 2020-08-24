package com.epam.controllers;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.epam.models.User;
import com.epam.models.dto.UserDto;
import com.epam.service.UserServiceImplementation;

@SpringBootTest
@AutoConfigureMockMvc
class UserControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private UserServiceImplementation userService;

	String exampleUserDtoJson = "{\"fname\":\"Test\",\"lname\":\"Test\",\"username\":\"Hyd\",\"password\":\"Test\",\"role\":\"1\"}";
	@Test
	void addUserReturnsResponseEntityCreated() throws Exception {
		User user=new User("Test", "test", "Test", "Test", 1);
		Mockito.when(userService.addUser(Mockito.any(UserDto.class))).thenReturn(user);
		RequestBuilder requestBuilder=MockMvcRequestBuilders
				.post("/api/v1/user")
				.accept(MediaType.APPLICATION_JSON)
				.content(exampleUserDtoJson)
				.contentType(MediaType.APPLICATION_JSON);
		MvcResult result=mockMvc.perform(requestBuilder).andReturn();
		assertEquals(HttpStatus.CREATED.value(), result.getResponse().getStatus());
		assertEquals("http://localhost/api/v1/user/0", result.getResponse().getHeader(HttpHeaders.LOCATION));
	}
	@Test
	void addUserReturnsResponseEntityNoContent() throws Exception {
		Mockito.when(userService.addUser(Mockito.any(UserDto.class))).thenReturn(null);
		RequestBuilder requestBuilder=MockMvcRequestBuilders
				.post("/api/v1/user")
				.accept(MediaType.APPLICATION_JSON)
				.content(exampleUserDtoJson)
				.contentType(MediaType.APPLICATION_JSON);
		MvcResult result=mockMvc.perform(requestBuilder).andReturn();
		assertEquals(HttpStatus.NO_CONTENT.value(), result.getResponse().getStatus());
	}
}
