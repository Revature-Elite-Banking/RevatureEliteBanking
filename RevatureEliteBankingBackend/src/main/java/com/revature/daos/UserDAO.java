package com.revature.daos;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.revature.models.User;

@Repository
public interface UserDAO extends JpaRepository<User, Integer> {

	User findByUsername(String username);
	//Optional<User> findByUserName(String username); 
	
}
