package com.epam.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.epam.models.Jobs;
import com.epam.models.User;

public interface JobRepository extends JpaRepository<Jobs,Integer>{
	List<Jobs> findByUser(User user);
}
