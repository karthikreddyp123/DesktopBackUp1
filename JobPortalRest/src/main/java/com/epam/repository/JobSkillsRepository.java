package com.epam.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.epam.models.JobSkills;
import com.epam.models.Jobs;

public interface JobSkillsRepository extends JpaRepository<JobSkills, Integer>{

	List<JobSkills> findByJobs(Jobs job);
}
