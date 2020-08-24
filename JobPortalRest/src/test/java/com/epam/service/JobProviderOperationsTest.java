package com.epam.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

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

import com.epam.models.JobSkills;
import com.epam.models.Jobs;
import com.epam.models.User;
import com.epam.models.dto.JobDto;
import com.epam.repository.JobRepository;
import com.epam.repository.JobSkillsRepository;
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
class JobProviderOperationsTest {

	@MockBean
	private JobRepository jobRepository;
	@MockBean
	private JobSkillsRepository jobSkillsRepository;
	@MockBean
	private CurrentSession currentSession;
	@Autowired
	@InjectMocks
	private JobProviderOperations jobProviderOperations; 
	@BeforeEach
	void initialize() {
		MockitoAnnotations.initMocks(this);
	}
	@Test
	void getJobByIdReturnsJobDto() {
		Jobs jobs=new Jobs();
		jobs.setJobId(1);
		jobs.setJobTitle("Test");
		jobs.setCompanyName("Test");
		jobs.setLocation("Hyderabad");
		List<JobSkills> jobSkillsList=new ArrayList<JobSkills>();
		jobSkillsList.add(new JobSkills("Test"));
		jobs.setSkillSet(jobSkillsList);
		when(jobRepository.findById(Mockito.anyInt())).thenReturn(Optional.of(jobs));
		assertEquals(1,jobProviderOperations.getJobById(1).getJobId());
	}
	@Test
	void getJobByIdReturnsEmptyJob() {
		when(jobRepository.findById(Mockito.anyInt())).thenReturn(Optional.empty());
		assertNotNull(jobProviderOperations.getJobById(1));
	}
	@Test
	void getAllJobsReturnsList() {
		List<Jobs> jobList=new ArrayList<Jobs>();
		Jobs jobs=new Jobs();
		jobs.setJobId(1);
		jobs.setJobTitle("Test");
		jobs.setCompanyName("Test");
		jobs.setLocation("Hyderabad");
		List<JobSkills> jobSkillsList=new ArrayList<JobSkills>();
		jobSkillsList.add(new JobSkills("Test"));
		jobs.setSkillSet(jobSkillsList);
		jobList.add(jobs);
		when(currentSession.getCurrentUser()).thenReturn(new User());
		when(jobRepository.findByUser(Mockito.any(User.class))).thenReturn(jobList);
		assertEquals(1,jobProviderOperations.getAllJobs().size());
	}

	@Test
	void addJobReturnsJob() {
		JobDto jobDto=new JobDto();
		jobDto.setJobId(1);
		jobDto.setJobTitle("Test");
		jobDto.setCompanyName("Test");
		jobDto.setLocation("Hyderabad");
		jobDto.setSkillSet("Test,Test");
		when(currentSession.getCurrentUser()).thenReturn(new User());
		when(jobRepository.save(Mockito.any(Jobs.class))).thenReturn(new Jobs("Test","Test","Test"));
		assertEquals("Test",jobProviderOperations.addJob(jobDto).getJobTitle());
	}
	@Test
	void updateJobReturnsJob() {
		JobDto jobDto=new JobDto();
		jobDto.setJobId(1);
		jobDto.setJobTitle("Test");
		jobDto.setCompanyName("Test");
		jobDto.setLocation("Hyderabad");
		jobDto.setSkillSet("Test,Test");	
		when(currentSession.getCurrentUser()).thenReturn(new User());
		Mockito.doNothing().when(jobSkillsRepository).deleteInBatch(Mockito.anyList());
		when(jobRepository.save(Mockito.any(Jobs.class))).thenReturn(new Jobs("Test","Test","Test"));
		assertEquals("Test",jobProviderOperations.updateJob(jobDto).getJobTitle());
	}
	@Test
	void deleteJobReturnsBoolean() {
		Mockito.doNothing().when(jobRepository).deleteById(Mockito.anyInt());
		assertEquals(true, jobProviderOperations.deleteJob(1));
	}
	@Test
	void deleteJobReturnsBooleanFalse() {
		Mockito.doThrow(IllegalArgumentException.class).when(jobRepository).deleteById(Mockito.anyInt());
		assertEquals(false,jobProviderOperations.deleteJob(1));
	}
	@Test
	void getRegisteredUsersReturnsList() {
		Set<User> users=new HashSet<User>();
		User user=new User("Test", "Test", "Test", "Test", 1);
		users.add(user);
		Jobs jobs=new Jobs();
		jobs.setJobId(1);
		jobs.setJobTitle("Test");
		jobs.setCompanyName("Test");
		jobs.setLocation("Hyderabad");
		List<JobSkills> jobSkillsList=new ArrayList<JobSkills>();
		jobSkillsList.add(new JobSkills("Test"));
		jobs.setSkillSet(jobSkillsList);
		jobs.setRegisteredUsers(users);
		when(jobRepository.findById(Mockito.anyInt())).thenReturn(Optional.of(jobs));
		assertEquals(1,jobProviderOperations.getRegisteredUsersForJob(1).size());
	}
	@Test
	void getRegisteredUsersReturnsEmptyList() {
		when(jobRepository.findById(Mockito.anyInt())).thenReturn(Optional.empty());
		assertEquals(0,jobProviderOperations.getRegisteredUsersForJob(1).size());
	}
}
