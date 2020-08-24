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
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.epam.models.dto.JobDto;
import com.epam.service.JobSeekerOperations;
@SpringBootTest
@AutoConfigureMockMvc
@WithMockUser(username = "Jitender@epam.com", roles = "JOBSEEKER")
class JobSeekerControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private JobSeekerOperations jobSeekerOperations;
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
		Mockito.when(jobSeekerOperations.getAllJobs()).thenReturn(jobsList);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
				"/jobseeker").accept(
				MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		String expected="[{\"jobId\":1,\"jobTitle\":\"Test\",\"companyName\":\"Test\",\"location\":\"Hyd\",\"skillSet\":\"Test,Test\"}]";
		JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);
	}
	@Test
	void getRegisteredJobsReturnsJsonArray() throws Exception {
		JobDto jobDto = new JobDto();
		jobDto.setJobId(1);
		jobDto.setJobTitle("Test");
		jobDto.setLocation("Hyd");
		jobDto.setCompanyName("Test");
		jobDto.setSkillSet("Test,Test");
		List<JobDto> jobsList=new ArrayList<JobDto>();
		jobsList.add(jobDto);
		Mockito.when(jobSeekerOperations.getRegisteredJobs()).thenReturn(jobsList);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
				"/jobseeker/getRegisteredJob").accept(
				MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		String expected="[{\"jobId\":1,\"jobTitle\":\"Test\",\"companyName\":\"Test\",\"location\":\"Hyd\",\"skillSet\":\"Test,Test\"}]";
		JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);
	}
	@Test
	void jobRegisterReturnsBoolean() throws Exception {
		Mockito.when(jobSeekerOperations.jobRegister(Mockito.anyInt())).thenReturn(true);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
				"/jobseeker/registerJob/1").accept(
				MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		assertEquals("true", result.getResponse().getContentAsString());
	}
	@Test
	void deleteJobReturnsResponseEntityOk() throws Exception {
		Mockito.when(jobSeekerOperations.jobRemoveRegistraion(Mockito.anyInt())).thenReturn(true);
		RequestBuilder requestBuilder=MockMvcRequestBuilders
				.get("/jobseeker/removeRegisteredJob/1")
				.accept(MediaType.APPLICATION_JSON);
		MvcResult result=mockMvc.perform(requestBuilder).andReturn();
		assertEquals(HttpStatus.OK.value(), result.getResponse().getStatus());
	}
	@Test
	void deleteJobReturnsResponseEntityNotFound() throws Exception {
		Mockito.when(jobSeekerOperations.jobRemoveRegistraion(Mockito.anyInt())).thenReturn(false);
		RequestBuilder requestBuilder=MockMvcRequestBuilders
				.get("/jobseeker/removeRegisteredJob/1")
				.accept(MediaType.APPLICATION_JSON);
		MvcResult result=mockMvc.perform(requestBuilder).andReturn();
		assertEquals(HttpStatus.NOT_FOUND.value(), result.getResponse().getStatus());
	}
	
}
