package com.epam;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.epam.service.JobDtoToJobPropertyMap;
import com.epam.service.JobToJobDtoPropertyMap;

@SpringBootApplication
public class JobPortalWebApplication {

	public static void main(String[] args) {
		SpringApplication.run(JobPortalWebApplication.class, args);
	}
	@Bean
	public ModelMapper getModelMapper() {
		ModelMapper modelMapper=new ModelMapper();
		modelMapper.addMappings(new JobDtoToJobPropertyMap());
		modelMapper.addMappings(new JobToJobDtoPropertyMap());
		return modelMapper;
	}
}
