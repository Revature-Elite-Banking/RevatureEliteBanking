package com.revature.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.daos.UserDAO;
import com.revature.models.User;
import com.revature.services.UserService;

@RestController
@RequestMapping(value="/user")
@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
public class Usercontroller {
	
	private UserService us;
	
	@Autowired
	public Usercontroller(UserService us) {
		
		super();
		this.us = us;
	}
	
	/*
	 * Returning list of Users
	 * as response
	 */
	@GetMapping
	public ResponseEntity<List<User>> getAllUser(){
		return ResponseEntity.status(200).body(us.findUserAll());
	}
	
	/*
	 * Inserting new user and sending an response in a body
	 * also, validating the existing username and email before adding a user
	 */
	@PostMapping
	public ResponseEntity addUser(@RequestBody User u) {
		String username = u.getUsername();
		String email = u.getEmail();
		if(us.findUserByUsername(username)!= null) {
			return ResponseEntity
		            .status(HttpStatus.FORBIDDEN)
		            .body("Username Already Exists");
		}else if(us.findUserByEmail(email) != null) {
			return ResponseEntity
		            .status(406)
		            .body("Email Already Exists");
		}
		else {
			us.insertUser(u);
			return ResponseEntity.status(201).build();
			
		}
		
		
	}
	
	/*
	 * Getting an user Information by username 
	 * and returning in a response
	 */
	@GetMapping(value="/{username}")
	public ResponseEntity<User> getUserByUsername(@PathVariable String username){
		
		User user = us.findUserByUsername(username);
		
		return ResponseEntity.ok(user);
		
	}

}
