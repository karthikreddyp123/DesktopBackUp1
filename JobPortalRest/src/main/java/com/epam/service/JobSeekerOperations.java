package com.epam.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.epam.models.Jobs;
import com.epam.models.User;
import com.epam.models.dto.JobDto;
import com.epam.repository.JobRepository;
import com.epam.repository.UserRepository;

@Service
public class JobSeekerOperations {
	@Autowired
	private JobRepository jobRepository;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private ModelMapper modelMapper; 
	@Autowired
	private CurrentSession currentSession;
	public List<JobDto> getAllJobs() {
		return jobRepository.findAll(Sort.by(Sort.Direction.ASC, "jobId")).stream()
				.map(job -> modelMapper.map(job, JobDto.class))
				.collect(Collectors.toList());
	}
	public boolean jobRegister(int jobId) {
		boolean isAlreadyRegistered=false;
		Optional<Jobs> jobOptional=jobRepository.findById(jobId);
		Jobs jobs=jobOptional.isPresent()?jobOptional.get():new Jobs();
		User user=currentSession.getCurrentUser();
		if(user.getRegisteredJobs().contains(jobs)) {
			isAlreadyRegistered=true;
		}
		else {
			jobs.getRegisteredUsers().add(user);
			user.getRegisteredJobs().add(jobs);
			jobRepository.save(jobs);
		}
		return isAlreadyRegistered;
	}
	public List<JobDto> getRegisteredJobs(){
		Optional<User> userOptional=userRepository.findById(currentSession.getCurrentUser().getUserId());
		User user=userOptional.isPresent()?userOptional.get():new User();
		return user.getRegisteredJobs().stream()
				.map(job -> modelMapper.map(job, JobDto.class))
				.collect(Collectors.toList());
	}
	public boolean jobRemoveRegistraion(int jobId) {
		boolean isDeleted=false;
		Optional<Jobs> jobOptional=jobRepository.findById(jobId);
		Jobs job=jobOptional.isPresent()?jobOptional.get():new Jobs();
		Optional<User> userOptional=userRepository.findById(currentSession.getCurrentUser().getUserId());
		User user=userOptional.isPresent()?userOptional.get():new User();
		if(job.getRegisteredUsers().remove(user)&&user.getRegisteredJobs().remove(job)) {
			isDeleted=true;
			jobRepository.save(job);
		}
		return isDeleted;
	}
}
