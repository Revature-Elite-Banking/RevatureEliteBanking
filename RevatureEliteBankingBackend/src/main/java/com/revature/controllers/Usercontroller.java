package com.revature.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
	
	@GetMapping
	public ResponseEntity<List<User>> getAllUser(){
		return ResponseEntity.status(200).body(us.findUserAll());
	}
	
	@PostMapping
	public ResponseEntity addUser(@RequestBody User u) {
		us.insertUser(u);
		return ResponseEntity.status(201).build();
		
	}
	
	@GetMapping(value="/{username}")
	public ResponseEntity<User> getUserByUsername(@PathVariable String username){
		
		User user = us.findUserByUsername(username);
		
		return ResponseEntity.ok(user);
		
	}

}
