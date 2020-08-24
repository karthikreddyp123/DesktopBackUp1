package com.example.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.Models.UserDetails;

public interface UserDetailsRepo extends JpaRepository<UserDetails, Integer>{

}
