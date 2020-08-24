package com.epam.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epam.models.Jobs;
import com.epam.models.User;
import com.epam.models.dto.JobDto;
import com.epam.models.dto.UserDto;
import com.epam.repository.JobRepository;
import com.epam.repository.JobSkillsRepository;

@Service
public class JobProviderOperations {

	@Autowired
	private JobRepository jobRepository;
	@Autowired
	private JobSkillsRepository jobSkillsRepository;
	@Autowired
	private ModelMapper modelMapper; 
	@Autowired
	private CurrentSession currentSession;
	public Jobs addJob(JobDto jobUiModel) {
		Jobs jobs=modelMapper.map(jobUiModel, Jobs.class);
		User user=currentSession.getCurrentUser();
		jobs.setCreatedDate(LocalDate.now());
		jobs.setUser(user);
		return jobRepository.save(jobs);
	}
	public List<JobDto> getAllJobs() {
		User user=currentSession.getCurrentUser();
		return jobRepository.findByUser(user).stream()
				.map(job -> modelMapper.map(job, JobDto.class))
				.collect(Collectors.toList());
	}
	public JobDto getJobById(int jobId) {
		Optional<Jobs> jobOptional=jobRepository.findById(jobId);
		Jobs job=jobOptional.isPresent()?jobOptional.get():new Jobs();
		return modelMapper.map(job,JobDto.class);
	}
	public Jobs updateJob(JobDto jobUiModel) {
		Jobs jobs=modelMapper.map(jobUiModel, Jobs.class);
		jobSkillsRepository.deleteInBatch(jobSkillsRepository.findByJobs(jobs));
		jobs.setCreatedDate(LocalDate.now());
		User user=currentSession.getCurrentUser();
		jobs.setUser(user);
		return jobRepository.save(jobs);

	}
	public boolean deleteJob(int jobId) {
		boolean isDeleted;
		try {
		jobRepository.deleteById(jobId);
		isDeleted=true;
		}
		catch(IllegalArgumentException ex) {
			isDeleted=false;
		}
		return isDeleted;
	}
	public List<UserDto> getRegisteredUsersForJob(int jobId) {
		Optional<Jobs> jobOptional=jobRepository.findById(jobId);
		Jobs jobs=jobOptional.isPresent()?jobOptional.get():new Jobs();
		return jobs.getRegisteredUsers().stream()
				.map(user -> modelMapper.map(user, UserDto.class))
				.collect(Collectors.toList());
	}
}
