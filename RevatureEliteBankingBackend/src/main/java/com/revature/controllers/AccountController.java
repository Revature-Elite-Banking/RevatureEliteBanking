package com.revature.controllers;

import java.util.List;
import java.util.Optional;

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

import com.revature.daos.AccountDAO;
import com.revature.daos.UserDAO;
import com.revature.models.Account;
import com.revature.services.AccountService;

@RestController
@RequestMapping(value="/account") 
@CrossOrigin
public class AccountController {
	//Remember that authentication is required for all requests other than registration and logins
	//Your HTTP requests to the back end should include the following key/value pair in the header
	//Key:Authorization
	//Value:Bearer <Their JWT>

	private AccountService AS;
	private UserDAO UDAO;
	
	
	@Autowired 
	public AccountController(AccountService AS, UserDAO udao) {
		super();
		this.AS = AS;
		this.UDAO = udao;
	}
	
	//The front end calls this for the account view
	//All accounts associated with a given username are returned to the front end
	@GetMapping("/view/{username}")
	public ResponseEntity<Optional<List<Account>>> getAccounts(@PathVariable("username") String username) {
		Optional<List<Account>> accounts = AS.getAccountsByUsername(username);
		
		
		if(accounts == null) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(accounts);
		} 
		return ResponseEntity.ok(accounts);
	}
	
	//Call this to create a new account from the front end
	//Note that account means checking/savings, not an account you log in with
	//The front end needs to send the account type and the starting balance 
	//The front end is gonna determine whether the starting balance is 0 or if an initial deposit is made on creation
	@PostMapping("/new/{username}")
	public void newAccount(@PathVariable("username") String username, @RequestBody Account input) {
		if (username.equals(null)||input.equals(null));
		else
			AS.newAccount(username, input);
	}
}
