package com.epam.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.epam.model.User;

public interface UserRepository extends JpaRepository<User, Long>{

}
