package com.app.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.pojos.User;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;


public interface UserRepository extends JpaRepository<User, Integer>{
    Optional<User> findByEmailIdAndPassword(String emailId, String password);
}
