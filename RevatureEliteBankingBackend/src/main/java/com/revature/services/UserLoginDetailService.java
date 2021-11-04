package com.revature.services;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.revature.daos.UserDAO;
import com.revature.models.User;


@Service
public class UserLoginDetailService implements UserDetailsService {
	
	@Autowired
	private UserDAO userDAO;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user  = userDAO.findByUsername(username);
		if(user== null) {
			throw new UsernameNotFoundException("User Not Found");
		}
		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), new ArrayList<>());
//		return new UserLoginDetail(user);
		
	}
	
	
	
}
