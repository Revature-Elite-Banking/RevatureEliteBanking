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
	
	
	public User insertUser(User u) {
		
		 return uDAO.save(u);
		
	}


	public List<User> findUserAll() {
		
		return uDAO.findAll();
		
	}
	
	public User findUserByUsername(String username) {
		return uDAO.findByUsername(username);
	}

}
