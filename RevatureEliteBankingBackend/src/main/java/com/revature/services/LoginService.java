package com.revature.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.daos.UserDAO;
import com.revature.models.User;

@Service
public class LoginService {
	
	private UserDAO uDao;
	
	@Autowired
	public LoginService(UserDAO uDao) {
		super();
		this.uDao = uDao;
	}

	public boolean checkCredentials(String username, String password) {
		User u = uDao.findByUsername(username);
		
		if(u == null) {
			return false;
		} else if (password.equals(u.getPassword())) {
			return true;
		} else {
			return false;
		}
	}
}
