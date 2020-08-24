package com.epam.controllers;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.epam.service.JobProviderOperations;
import com.fasterxml.jackson.databind.ObjectMapper;
@SpringBootTest
@AutoConfigureMockMvc
@WithMockUser(username = "Karthik@epam.com", roles = "JOBPROVIDER")

class LoginTest {
	@Autowired
	private MockMvc mockMvc;
	@MockBean
	private JobProviderOperations jobProviderOperations;
	@Mock
	private ObjectMapper objectMapper;
	@Test
	void loginReturnsOk() throws Exception {
		String loginJson=" {" + "\"username\":\"Karthik@epam.com\"," + "\"password\":\"1234\"" + "}";
		RequestBuilder requestBuilder=MockMvcRequestBuilders
				.post("/login")
				.accept(MediaType.APPLICATION_JSON)
				.content(loginJson)
				.contentType(MediaType.APPLICATION_JSON);
		MvcResult result=mockMvc.perform(requestBuilder).andReturn();
		assertEquals(HttpStatus.OK.value(), result.getResponse().getStatus());
	}
	@Test
	@WithMockUser(username = "Jitender@gmail.com", roles = "JOBSEEKER")
	void loginWithJobSeekerRole() throws Exception {
		String loginJson=" {" + "\"username\":\"Jitender@gmail.com\"," + "\"password\":\"1234\"" + "}";
		RequestBuilder requestBuilder=MockMvcRequestBuilders
				.post("/login")
				.accept(MediaType.APPLICATION_JSON)
				.content(loginJson)
				.contentType(MediaType.APPLICATION_JSON);
		MvcResult result=mockMvc.perform(requestBuilder).andReturn();
		assertEquals(HttpStatus.OK.value(), result.getResponse().getStatus());
	}
	@Test
	@WithMockUser(username = "Jitender123@gmail.com", roles = "JOBSEEKER")
	void loginReturnsUnAuthorized() throws Exception {
		String loginJson=" {" + "\"username\":\"Jitender123@gmail.com\"," + "\"password\":\"1234\"" + "}";
		RequestBuilder requestBuilder=MockMvcRequestBuilders
				.post("/login")
				.accept(MediaType.APPLICATION_JSON)
				.content(loginJson)
				.contentType(MediaType.APPLICATION_JSON);
		MvcResult result=mockMvc.perform(requestBuilder).andReturn();
		assertEquals(HttpStatus.UNAUTHORIZED.value(), result.getResponse().getStatus());
	}
	@Test
	void deleteJobReturnsResponseEntityNotFoundWithBearerToken() throws Exception {
		Mockito.when(jobProviderOperations.deleteJob(Mockito.anyInt())).thenReturn(false);
		RequestBuilder requestBuilder=MockMvcRequestBuilders
				.get("/jobprovider/deleteJob/1")
				.accept(MediaType.APPLICATION_JSON)
				.header("Authorization", "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJLYXJ0aGlrQGVwYW0uY29tIiwiZXhwIjoxNTk2MzUwNDQ2fQ.6kAqBr6F0Y93hMQ0qU2KNazyeT_jGxBLI5zl2kqH4OJbBdhVzVOpWaNG5K7FPRloxsCFc2j8exWDPRgKcadKpQ");
		MvcResult result=mockMvc.perform(requestBuilder).andReturn();
		assertEquals(HttpStatus.NOT_FOUND.value(), result.getResponse().getStatus());
	}
	@Test
	void deleteJobReturnsResponseEntityNotFoundWithoutBearerToken() throws Exception {
		Mockito.when(jobProviderOperations.deleteJob(Mockito.anyInt())).thenReturn(false);
		RequestBuilder requestBuilder=MockMvcRequestBuilders
				.get("/jobprovider/deleteJob/1")
				.accept(MediaType.APPLICATION_JSON);
		MvcResult result=mockMvc.perform(requestBuilder).andReturn();
		assertEquals(HttpStatus.NOT_FOUND.value(), result.getResponse().getStatus());
	}
	@Test
	void deleteJobReturnsResponseEntityNotFoundWithInValidBearerToken() throws Exception {
		Mockito.when(jobProviderOperations.deleteJob(Mockito.anyInt())).thenReturn(false);
		RequestBuilder requestBuilder=MockMvcRequestBuilders
				.get("/jobprovider/deleteJob/1")
				.accept(MediaType.APPLICATION_JSON)
				.header("Authorization", "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJLYXJ0aGlrQGVwYW0uY29tIiwiZXhwIjoxNTk2MzUwNDQ2fQ.6kAqBr6F0Y93hMQ0qU2KNazyeT_jGxBLI5zl2kqH4OJbBdhVzVOpWaNG5K7FPRloxsCFc2j8exWDPRgKcadKpQ");
		MvcResult result=mockMvc.perform(requestBuilder).andReturn();
		assertEquals(HttpStatus.NOT_FOUND.value(), result.getResponse().getStatus());
	}
}
