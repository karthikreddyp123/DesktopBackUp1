package com.epam.controllers;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.epam.models.Jobs;
import com.epam.models.dto.JobDto;
import com.epam.models.dto.UserDto;
import com.epam.service.JobProviderOperations;
@SpringBootTest
@AutoConfigureMockMvc
@WithMockUser(username = "Karthik@epam.com", roles = "JOBPROVIDER")
class JobProviderControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private JobProviderOperations jobProviderOperations;
	
	String exampleJobDtoJson = "{\"jobTitle\":\"Test\",\"companyName\":\"Test\",\"location\":\"Hyd\",\"skillSet\":\"Test,Test\"}";
	@Test
	void getAllJobsReturnsJsonArray() throws Exception {
		JobDto jobDto = new JobDto();
		jobDto.setJobId(1);
		jobDto.setJobTitle("Test");
		jobDto.setLocation("Hyd");
		jobDto.setCompanyName("Test");
		jobDto.setSkillSet("Test,Test");
		List<JobDto> jobsList=new ArrayList<JobDto>();
		jobsList.add(jobDto);
		Mockito.when(jobProviderOperations.getAllJobs()).thenReturn(jobsList);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
				"/jobprovider").accept(
				MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		String expected="[{\"jobId\":1,\"jobTitle\":\"Test\",\"companyName\":\"Test\",\"location\":\"Hyd\",\"skillSet\":\"Test,Test\"}]";
		JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);
	}
	
	@Test
	void getRegisteredUsersReturnsJsonArray() throws Exception {
		UserDto userDto = new UserDto(1,"Karthik","Reddy","Karthik@epam.com",1);
		List<UserDto> userList=new ArrayList<UserDto>();
		userList.add(userDto);
		Mockito.when(jobProviderOperations.getRegisteredUsersForJob(Mockito.anyInt())).thenReturn(userList);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
				"/jobprovider/getRegisteredUsers/1").accept(
				MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		String expected="[{\"userId\":1,\"fname\":\"Karthik\",\"lname\":\"Reddy\",\"username\":\"Karthik@epam.com\",\"password\":null,\"role\":1}]";
		JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);
		
	}
	@Test
	void addJobReturnsResponseEntityCreated() throws Exception {
		Jobs jobs=new Jobs("Test","Test","Test");
		Mockito.when(jobProviderOperations.addJob(Mockito.any(JobDto.class))).thenReturn(jobs);
		RequestBuilder requestBuilder=MockMvcRequestBuilders
				.post("/jobprovider/addJob")
				.accept(MediaType.APPLICATION_JSON)
				.content(exampleJobDtoJson)
				.contentType(MediaType.APPLICATION_JSON);
		MvcResult result=mockMvc.perform(requestBuilder).andReturn();
		assertEquals(HttpStatus.CREATED.value(), result.getResponse().getStatus());
		assertEquals("http://localhost/jobprovider/addJob/0", result.getResponse().getHeader(HttpHeaders.LOCATION));
	}
	@Test
	void addJobReturnsResponseEntityNoContent() throws Exception {
		Mockito.when(jobProviderOperations.addJob(Mockito.any(JobDto.class))).thenReturn(null);
		RequestBuilder requestBuilder=MockMvcRequestBuilders
				.post("/jobprovider/addJob")
				.accept(MediaType.APPLICATION_JSON)
				.content(exampleJobDtoJson)
				.contentType(MediaType.APPLICATION_JSON);
		MvcResult result=mockMvc.perform(requestBuilder).andReturn();
		assertEquals(HttpStatus.NO_CONTENT.value(), result.getResponse().getStatus());
	}
	@Test
	void updateJobSaveReturnsResponseEntityCreated() throws Exception {
		Jobs jobs=new Jobs("Test","Test","Test");
		Mockito.when(jobProviderOperations.updateJob(Mockito.any(JobDto.class))).thenReturn(jobs);
		RequestBuilder requestBuilder=MockMvcRequestBuilders
				.post("/jobprovider/updateJobSave")
				.accept(MediaType.APPLICATION_JSON)
				.content(exampleJobDtoJson)
				.contentType(MediaType.APPLICATION_JSON);
		MvcResult result=mockMvc.perform(requestBuilder).andReturn();
		assertEquals(HttpStatus.CREATED.value(), result.getResponse().getStatus());
		assertEquals("http://localhost/jobprovider/updateJobSave/0", result.getResponse().getHeader(HttpHeaders.LOCATION));
	}
	@Test
	void updateJobSaveReturnsResponseEntityNoContent() throws Exception {
		Mockito.when(jobProviderOperations.updateJob(Mockito.any(JobDto.class))).thenReturn(null);
		RequestBuilder requestBuilder=MockMvcRequestBuilders
				.post("/jobprovider/updateJobSave")
				.accept(MediaType.APPLICATION_JSON)
				.content(exampleJobDtoJson)
				.contentType(MediaType.APPLICATION_JSON);
		MvcResult result=mockMvc.perform(requestBuilder).andReturn();
		assertEquals(HttpStatus.NO_CONTENT.value(), result.getResponse().getStatus());
	}
	@Test
	void deleteJobReturnsResponseEntityOk() throws Exception {
		Mockito.when(jobProviderOperations.deleteJob(Mockito.anyInt())).thenReturn(true);
		RequestBuilder requestBuilder=MockMvcRequestBuilders
				.get("/jobprovider/deleteJob/1")
				.accept(MediaType.APPLICATION_JSON);
		MvcResult result=mockMvc.perform(requestBuilder).andReturn();
		assertEquals(HttpStatus.OK.value(), result.getResponse().getStatus());
	}
	@Test
	void deleteJobReturnsResponseEntityNotFound() throws Exception {
		Mockito.when(jobProviderOperations.deleteJob(Mockito.anyInt())).thenReturn(false);
		RequestBuilder requestBuilder=MockMvcRequestBuilders
				.get("/jobprovider/deleteJob/1")
				.accept(MediaType.APPLICATION_JSON);
		MvcResult result=mockMvc.perform(requestBuilder).andReturn();
		assertEquals(HttpStatus.NOT_FOUND.value(), result.getResponse().getStatus());
	}
}
