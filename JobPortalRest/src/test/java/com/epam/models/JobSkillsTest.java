package com.epam.models;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;

class JobSkillsTest {

	@Test
	void getterSetterTest() {
		JobSkills jobSkills=new JobSkills();
		jobSkills=new JobSkills("Test");
		jobSkills.setSkill("Test");
		jobSkills.setSkillId(1);
		Jobs jobs=new Jobs("Test", "Test", "Test");
		jobSkills.setJobs(jobs);
		assertEquals("Test", jobSkills.getSkill());
		assertEquals(1, jobSkills.getSkillId());
		assertEquals(jobs, jobSkills.getJobs());
		assertEquals("JobSkills{id=1, skill='Test'}", jobSkills.toString());
	}

}
