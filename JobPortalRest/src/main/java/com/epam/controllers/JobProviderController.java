package com.epam.controllers;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.epam.models.Jobs;
import com.epam.models.dto.JobDto;
import com.epam.models.dto.UserDto;
import com.epam.service.JobProviderOperations;

@RestController
public class JobProviderController {
	@Autowired
	private JobProviderOperations jobProviderOperations;
	@PostMapping("/jobprovider/addJob")
	public ResponseEntity<Void> addJob(@RequestBody JobDto jobUiModel) {
		Jobs jobs=jobProviderOperations.addJob(jobUiModel);
		if(jobs==null) {
			return ResponseEntity.noContent().build();
		}
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path(
				"/{id}").buildAndExpand(jobs.getJobId()).toUri();

		return ResponseEntity.created(location).build();
	}
	@GetMapping("/jobprovider")
	public List<JobDto> getAllJobs() {
		return jobProviderOperations.getAllJobs();
	}
	@PostMapping("/jobprovider/updateJobSave")
	public ResponseEntity<Void> updateJobSave(@RequestBody JobDto jobUiModel) {
		Jobs jobs=jobProviderOperations.updateJob(jobUiModel);
		if(jobs==null) {
			return ResponseEntity.noContent().build();
		}
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path(
				"/{id}").buildAndExpand(jobs.getJobId()).toUri();
		return ResponseEntity.created(location).build();
	}
	@RequestMapping("/jobprovider/deleteJob/{jobId}")
	public ResponseEntity<Void> deleteJob(@PathVariable int jobId) {
		boolean isDeleted=jobProviderOperations.deleteJob(jobId);
		if(!isDeleted) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok().build();
	}
	@GetMapping("/jobprovider/getRegisteredUsers/{jobId}")
	public List<UserDto> getRegisteredUsers(@PathVariable int jobId) {
		return jobProviderOperations.getRegisteredUsersForJob(jobId);
	}
}
