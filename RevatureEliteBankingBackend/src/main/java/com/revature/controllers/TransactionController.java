package com.revature.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.models.Transaction;
import com.revature.services.TransactionService;

@RestController // combines @Controller and @ResponseBody, looks cleaner
@RequestMapping(value="/transaction") // specfies the endpoint for the controller (transaction in this case)
@CrossOrigin(origins="http://localhost:4200/", allowCredentials="true")
public class TransactionController {
	
	private TransactionService tService;
	
	@Autowired
	public TransactionController(TransactionService tService) {
		this.tService = tService;
	}
	
	// get all transactions (probably wont use but here for potential tests)
	@GetMapping
	public ResponseEntity<List<Transaction>> getAllTransactions() {
		List<Transaction> t = tService.getAllTransactions();
		
		if(t == null) {
			return ResponseEntity.status(422).body(null);
		}
		
		return ResponseEntity.status(200).body(t);
	}
	
	// get a specific transaction based on id
	@GetMapping("/{id}")
	public ResponseEntity<Transaction> getTransactionById(@PathVariable int id) {
		Transaction t = tService.getTransactionById(id);
		
		if(t == null) {
			return ResponseEntity.status(422).body(null);
		}
		
		return ResponseEntity.status(200).body(t);
	}
	
	// get transactions by account
	@GetMapping("/account/{account_id}")
	public ResponseEntity<List<Transaction>> getTransactionsByAccount(@PathVariable int account_id) {
		List<Transaction> t = tService.getTransactionsByAccount(account_id);
		
		if(t == null) {
			return ResponseEntity.status(422).body(null);
		}
		
		return ResponseEntity.status(200).body(t);
		
	}
	
	// get transactions by user
	@GetMapping("/user/{user_id}")
	public ResponseEntity<List<Transaction>> getUserTransactionHistory(@PathVariable int user_id) {
		List<Transaction> t = tService.getUserTransactionHistory(user_id);
		
		if(t == null) {
			return ResponseEntity.status(422).body(null);
		}
		
		return ResponseEntity.status(200).body(t);
	}
	
	// add a new transaction
	@PostMapping("/add")
	public ResponseEntity<Transaction> addTransaction(@RequestBody Transaction tran) {
		Transaction t = tService.addTransaction(tran);
		
		if(t == null) {
			return ResponseEntity.status(422).body(null);
		}
		
		return ResponseEntity.status(201).body(t);
	}
	
	// delete a transaction (probably wont be used but here for potential tests)
	@DeleteMapping("/{id}")
	public ResponseEntity<Transaction> deleteTransaction(@PathVariable int id) {
		Transaction t = tService.deleteTransaction(id);
		
		if(t == null) {
			return ResponseEntity.status(422).body(null);
		}
		
		return ResponseEntity.status(200).body(t);
	}
}
