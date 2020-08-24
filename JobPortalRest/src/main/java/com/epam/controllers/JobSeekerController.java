package com.epam.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.epam.models.dto.JobDto;
import com.epam.service.JobSeekerOperations;
@RestController
public class JobSeekerController {
	@Autowired
	private JobSeekerOperations jobSeekerOperations;
	@GetMapping("/jobseeker")
	public List<JobDto> getAllJobs() {
		return jobSeekerOperations.getAllJobs();
	}
	
	@GetMapping("/jobseeker/registerJob/{jobId}")
	public boolean jobRegister(@PathVariable int jobId) {
		return jobSeekerOperations.jobRegister(jobId);
		
	}
	@GetMapping("/jobseeker/getRegisteredJob")
	public List<JobDto> getRegisteredJobs() {
		return jobSeekerOperations.getRegisteredJobs();
	}
	@RequestMapping("/jobseeker/removeRegisteredJob/{jobId}")
	public ResponseEntity<Void> removeJobRegistration(@PathVariable int jobId) {
		boolean isDeleted=jobSeekerOperations.jobRemoveRegistraion(jobId);
		if(!isDeleted) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok().build();
	}
}
