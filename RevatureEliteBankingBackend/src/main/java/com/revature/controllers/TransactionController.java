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
	
	// get all transactions
	// GetMapping means this method will be called for a Get request
	@GetMapping
	public ResponseEntity<List<Transaction>> getAllTransactions() {
		List<Transaction> t = tService.getAllTransactions();
		
		return ResponseEntity.status(200).body(t);
	}
	
	@GetMapping("/{id}") // -- /transaction/5 -> returns the transaction with id = 5
	public ResponseEntity<Transaction> getTransactionById(@PathVariable int id) {
		@SuppressWarnings("unused")
		Transaction t = tService.getTransactionById(id);
		return null;
	}
	
	@PostMapping("/add") // -- /transaction/add
	public ResponseEntity<Transaction> addTransaction(@RequestBody Transaction tran) {
		@SuppressWarnings("unused")
		Transaction t = tService.addTransaction(tran);
		return null;
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Transaction> deleteTransaction(@PathVariable int id) {
		@SuppressWarnings("unused")
		Transaction t = tService.deleteTransaction(id);
		return null;
	}
}
