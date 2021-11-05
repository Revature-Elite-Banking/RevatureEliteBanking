package com.revature.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.daos.AccountDAO;
import com.revature.daos.UserDAO;
import com.revature.models.Account;

@RestController
@RequestMapping(value="/account") 
@CrossOrigin
public class AccountController {

	private AccountDAO ADAO;
	private UserDAO UDAO;
	
	
	@Autowired 
	public AccountController(AccountDAO dao, UserDAO udao) {
		super();
		this.ADAO = dao;
		this.UDAO = udao;
	}
	
	
	@GetMapping("/{username}")
	public ResponseEntity<Account> getAccounts(@PathVariable("username") String username) {
		System.out.println("This handler connects"); //Used to test if the handler is set up correctly
		
		
		/*if(a == null) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(a);
		} */
		return null;
	}
}
