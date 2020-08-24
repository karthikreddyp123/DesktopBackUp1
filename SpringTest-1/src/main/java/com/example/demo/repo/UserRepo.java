package com.example.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.Models.Users;

public interface UserRepo extends JpaRepository<Users, Integer> {

	Users findByUserName(String userName);
}
