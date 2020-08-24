package com.epam.service;

import java.util.List;
import java.util.stream.Collectors;


import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.epam.models.Jobs;
import com.epam.models.dto.JobDto;
import com.epam.repository.JobRepository;
import com.epam.repository.JobSkillsRepository;

@Service
public class JobPortalServiceImplementation {

	@Autowired
	private JobRepository jobRepository;
	@Autowired
	private JobSkillsRepository jobSkillsRepository;
	@Autowired
	private ModelMapper modelMapper;

	public void addJob(JobDto jobUiModel) {
		Jobs jobs=modelMapper.map(jobUiModel, Jobs.class);
		jobRepository.save(jobs);
	}
	public List<JobDto> getAllJobs() {
		return jobRepository.findAll(Sort.by(Sort.Direction.ASC, "jobId")).stream()
				  .map(job -> modelMapper.map(job, JobDto.class))
				  .collect(Collectors.toList());
	}
	public JobDto getJobById(int jobId) {
		Jobs job=jobRepository.getOne(jobId);
		return modelMapper.map(job,JobDto.class);
	}
	public void updateJob(JobDto jobUiModel) {
		Jobs jobs=modelMapper.map(jobUiModel, Jobs.class);
		jobSkillsRepository.deleteInBatch(jobSkillsRepository.findByJobs(jobs));
		jobRepository.save(jobs);

	}
	public void deleteJob(int jobId) {
		jobRepository.deleteById(jobId);
	}
}
