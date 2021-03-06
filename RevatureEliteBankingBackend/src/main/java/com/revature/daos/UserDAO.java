package com.revature.daos;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.revature.models.User;

@Repository
public interface UserDAO extends JpaRepository<User, Integer> {

	
//	Optional<User> findByUsername(String username); 

	public User findByUsername(String username);

	public User findByEmail(String email);
	
}
