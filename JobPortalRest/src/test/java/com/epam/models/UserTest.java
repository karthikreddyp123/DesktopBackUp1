package com.epam.models;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Test;

class UserTest {

	@Test
	void getterSetterTest() {
		User user=new User();
		user = new User("Test", "Test", "Test", "Test", 1);
		user.setFname("Test");
		user.setLname("Test");
		user.setPassword("Test");
		user.setRole(1);
		user.setUserId(1);
		user.setUsername("Test");
		Jobs jobs=new Jobs("Test", "Test", "Test");
		List<Jobs> jobList=new ArrayList<Jobs>();
		jobList.add(jobs);
		user.setJobs(jobList);
		Set<Jobs> jobSet=new HashSet<Jobs>();
		jobSet.add(jobs);
		user.setRegisteredJobs(jobSet);
		assertEquals("Test", user.getFname());
		assertEquals("Test", user.getLname());
		assertEquals("Test", user.getPassword());
		assertEquals("Test", user.getUsername());	
		assertEquals(1, user.getRole());
		assertEquals(1, user.getUserId());
		assertEquals(jobList, user.getJobs());
		assertEquals(jobSet, user.getRegisteredJobs());
		assertEquals("User [userId=1, fname=Test, lname=Test, username=Test, password=Test, registeredJobs=[Jobs [jobId=0, jobTitle=Test, companyName=Test, location=Test]]]", user.toString());
	}

}
