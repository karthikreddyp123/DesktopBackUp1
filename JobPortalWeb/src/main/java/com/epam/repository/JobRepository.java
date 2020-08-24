package com.epam.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.epam.models.Jobs;

public interface JobRepository extends JpaRepository<Jobs,Integer>{
	
}
