package com.epam.models;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Test;

class JobsTest {

	@Test
	void getterSetterTest() {
		Jobs jobConstructor=new Jobs("Test", "Test", "Test");
		Jobs jobs=new Jobs();
		jobs.setCompanyName("Test");
		jobs.setJobId(1);
		jobs.setJobTitle("Test");
		jobs.setLocation("Test");
		LocalDate date=LocalDate.now();
		jobs.setCreatedDate(date);
		User user=new User("Test","Test","Test","Test",1);
		jobs.setUser(user);
		Set<User> users=new HashSet<User>();
		users.add(user);
		jobs.setRegisteredUsers(users);
		JobSkills jobSkills=new JobSkills("Test");
		List<JobSkills> jobList=new ArrayList<JobSkills>();
		jobList.add(jobSkills);
		jobs.setSkillSet(jobList);
		assertEquals("Test", jobs.getCompanyName());
		assertEquals("Test", jobs.getJobTitle());
		assertEquals("Test", jobs.getLocation());
		assertEquals(1, jobs.getJobId());
		assertEquals(date, jobs.getCreatedDate());
		assertEquals(user, jobs.getUser());
		assertEquals(users, jobs.getRegisteredUsers());
		assertEquals(jobList, jobs.getSkillSet());
		assertEquals("Jobs [jobId=0, jobTitle=Test, companyName=Test, location=Test]", jobConstructor.toString());
	}

}
