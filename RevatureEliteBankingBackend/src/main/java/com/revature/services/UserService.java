package com.revature.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.daos.UserDAO;
import com.revature.models.User;

@Service
public class UserService {
	
	private UserDAO uDAO;
	
	
	@Autowired
	public UserService(UserDAO uDAO) {
		super();
		this.uDAO = uDAO;
	}
	
	/*
	 * New User details is added to database
	 */
	public User insertUser(User u) {
		
		 return uDAO.save(u);
		
	}

	/*
	 * Listing all the User
	 */
	public List<User> findUserAll() {
		
		return uDAO.findAll();
		
	}

	
	/*
	 * returning user details by username
	 */
	public User findUserByUsername(String username) {
		
		return uDAO.findByUsername(username);
	}
	
	/*
	 * returning user details by Email
	 */
	public User findUserByEmail(String email) {
		
		return uDAO.findByEmail(email);
		
	};

}
