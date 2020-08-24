package com.epam.service;

import static org.junit.Assert.assertEquals;
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
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.epam.models.JobSkills;
import com.epam.models.Jobs;
import com.epam.models.User;
import com.epam.repository.JobRepository;
import com.epam.repository.UserRepository;
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
class JobSeekerOperationsTest {
	@MockBean
	private JobRepository jobRepository;
	@MockBean
	private UserRepository userRepository;
	@MockBean
	private CurrentSession currentSession;
	@Autowired
	@InjectMocks
	private JobSeekerOperations jobSeekerOperations; 
	@BeforeEach
	void initialize() {
		MockitoAnnotations.initMocks(this);
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
		when(jobRepository.findAll(Sort.by(Sort.Direction.ASC, "jobId"))).thenReturn(jobList);
		assertEquals(1,jobSeekerOperations.getAllJobs().size());
	}

	@Test
	void jobRegisterReturnsBooleanFalse() {
		List<Jobs> jobList=new ArrayList<Jobs>();
		Jobs jobs=new Jobs();
		jobs.setJobId(1);
		jobs.setJobTitle("Test");
		jobs.setCompanyName("Test");
		jobs.setLocation("Hyderabad");
		List<JobSkills> jobSkillsList=new ArrayList<JobSkills>();
		jobSkillsList.add(new JobSkills("Test"));
		jobs.setSkillSet(jobSkillsList);
		jobs.setRegisteredUsers(new HashSet<User>());
		User user=new User("Test", "Test", "Test", "Test", 1);
		user.setRegisteredJobs(new HashSet<Jobs>());
		jobList.add(jobs);
		when(currentSession.getCurrentUser()).thenReturn(user);
		when(jobRepository.findById(Mockito.anyInt())).thenReturn(Optional.of(jobs));
		assertEquals(false,jobSeekerOperations.jobRegister(1));
	}
	@Test
	void jobRegisterReturnsBooleanTrue() {
		Set<Jobs> jobList=new HashSet<Jobs>();
		Jobs jobs=new Jobs();
		jobs.setJobId(1);
		jobs.setJobTitle("Test");
		jobs.setCompanyName("Test");
		jobs.setLocation("Hyderabad");
		List<JobSkills> jobSkillsList=new ArrayList<JobSkills>();
		jobSkillsList.add(new JobSkills("Test"));
		jobs.setSkillSet(jobSkillsList);
		jobList.add(jobs);
		jobs.setRegisteredUsers(new HashSet<User>());
		User user=new User("Test", "Test", "Test", "Test", 1);
		user.setRegisteredJobs(jobList);
		
		when(currentSession.getCurrentUser()).thenReturn(user);
		when(jobRepository.findById(Mockito.anyInt())).thenReturn(Optional.of(jobs));
		assertEquals(true,jobSeekerOperations.jobRegister(1));
	}
	@Test
	void jobRegisterOptionalReturnsBooleanFalse() {
		Set<Jobs> jobList=new HashSet<Jobs>();
		Jobs jobs=new Jobs();
		jobs.setJobId(1);
		jobs.setJobTitle("Test");
		jobs.setCompanyName("Test");
		jobs.setLocation("Hyderabad");
		List<JobSkills> jobSkillsList=new ArrayList<JobSkills>();
		jobSkillsList.add(new JobSkills("Test"));
		jobs.setSkillSet(jobSkillsList);
		jobList.add(jobs);
		jobs.setRegisteredUsers(new HashSet<User>());
		User user=new User("Test", "Test", "Test", "Test", 1);
		user.setRegisteredJobs(jobList);
		when(currentSession.getCurrentUser()).thenReturn(user);
		when(jobRepository.findById(Mockito.anyInt())).thenReturn(Optional.empty());
		assertEquals(false,jobSeekerOperations.jobRegister(1));
	}
	@Test
	void getRegisteredJobsReturnsList() {
		Set<Jobs> jobList=new HashSet<Jobs>();
		Jobs jobs=new Jobs();
		jobs.setJobId(1);
		jobs.setJobTitle("Test");
		jobs.setCompanyName("Test");
		jobs.setLocation("Hyderabad");
		List<JobSkills> jobSkillsList=new ArrayList<JobSkills>();
		jobSkillsList.add(new JobSkills("Test"));
		jobs.setSkillSet(jobSkillsList);
		jobList.add(jobs);
		User user=new User("Test", "Test", "Test", "Test", 1);
		user.setRegisteredJobs(jobList);
		when(currentSession.getCurrentUser()).thenReturn(user);
		when(userRepository.findById(Mockito.anyInt())).thenReturn(Optional.of(user));
		assertEquals(1,jobSeekerOperations.getRegisteredJobs().size());
	}
	@Test
	void getRegisteredJobsReturnsEmptyList() {
		Set<Jobs> jobList=new HashSet<Jobs>();
		Jobs jobs=new Jobs();
		jobs.setJobId(1);
		jobs.setJobTitle("Test");
		jobs.setCompanyName("Test");
		jobs.setLocation("Hyderabad");
		List<JobSkills> jobSkillsList=new ArrayList<JobSkills>();
		jobSkillsList.add(new JobSkills("Test"));
		jobs.setSkillSet(jobSkillsList);
		jobList.add(jobs);
		User user=new User("Test", "Test", "Test", "Test", 1);
		user.setRegisteredJobs(jobList);
		when(currentSession.getCurrentUser()).thenReturn(user);
		when(userRepository.findById(Mockito.anyInt())).thenReturn(Optional.empty());
		assertEquals(0,jobSeekerOperations.getRegisteredJobs().size());
	}
	@Test
	void jobRemoveRegistrationReturnsBoolean() {
		Set<Jobs> jobList=new HashSet<Jobs>();
		Jobs jobs=new Jobs();
		jobs.setJobId(1);
		jobs.setJobTitle("Test");
		jobs.setCompanyName("Test");
		jobs.setLocation("Hyderabad");
		List<JobSkills> jobSkillsList=new ArrayList<JobSkills>();
		jobSkillsList.add(new JobSkills("Test"));
		jobs.setSkillSet(jobSkillsList);
		jobList.add(jobs);
		Set<User> users=new HashSet<User>();
		User user=new User("Test", "Test", "Test", "Test", 1);
		user.setRegisteredJobs(jobList);
		users.add(user);
		jobs.setRegisteredUsers(users);
		when(currentSession.getCurrentUser()).thenReturn(user);
		when(jobRepository.findById(Mockito.anyInt())).thenReturn(Optional.of(jobs));
		when(userRepository.findById(Mockito.anyInt())).thenReturn(Optional.of(user));
		Mockito.doReturn(jobs).when(jobRepository).save(Mockito.any(Jobs.class));
		assertEquals(true,jobSeekerOperations.jobRemoveRegistraion(1));
	}
	@Test
	void jobRemoveRegistrationReturnsFalse() {
		Set<Jobs> jobList=new HashSet<Jobs>();
		Jobs jobs=new Jobs();
		jobs.setJobId(1);
		jobs.setJobTitle("Test");
		jobs.setCompanyName("Test");
		jobs.setLocation("Hyderabad");
		List<JobSkills> jobSkillsList=new ArrayList<JobSkills>();
		jobSkillsList.add(new JobSkills("Test"));
		jobs.setSkillSet(jobSkillsList);
		jobList.add(jobs);
		Set<User> users=new HashSet<User>();
		User user=new User("Test", "Test", "Test", "Test", 1);
		user.setRegisteredJobs(new HashSet<Jobs>());
		users.add(user);
		jobs.setRegisteredUsers(new HashSet<User>());
		when(currentSession.getCurrentUser()).thenReturn(user);
		when(jobRepository.findById(Mockito.anyInt())).thenReturn(Optional.of(jobs));
		when(userRepository.findById(Mockito.anyInt())).thenReturn(Optional.of(user));
		assertEquals(false,jobSeekerOperations.jobRemoveRegistraion(1));
	}
	@Test
	void jobRemoveRegistrationOptionalReturnsFalse() {
		Set<Jobs> jobList=new HashSet<Jobs>();
		Jobs jobs=new Jobs();
		jobs.setJobId(1);
		jobs.setJobTitle("Test");
		jobs.setCompanyName("Test");
		jobs.setLocation("Hyderabad");
		List<JobSkills> jobSkillsList=new ArrayList<JobSkills>();
		jobSkillsList.add(new JobSkills("Test"));
		jobs.setSkillSet(jobSkillsList);
		jobList.add(jobs);
		Set<User> users=new HashSet<User>();
		User user=new User("Test", "Test", "Test", "Test", 1);
		user.setRegisteredJobs(new HashSet<Jobs>());
		users.add(user);
		jobs.setRegisteredUsers(new HashSet<User>());
		when(currentSession.getCurrentUser()).thenReturn(user);
		when(jobRepository.findById(Mockito.anyInt())).thenReturn(Optional.empty());
		when(userRepository.findById(Mockito.anyInt())).thenReturn(Optional.empty());
		assertEquals(false,jobSeekerOperations.jobRemoveRegistraion(1));
	}
}
