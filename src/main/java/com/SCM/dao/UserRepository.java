package com.SCM.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.SCM.entities.User;

public interface UserRepository extends JpaRepository<User, Integer> {

}
