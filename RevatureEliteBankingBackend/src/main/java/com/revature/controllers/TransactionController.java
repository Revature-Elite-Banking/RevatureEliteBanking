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

import com.revature.models.Account;
import com.revature.models.Transaction;
import com.revature.models.TransferDTO;
import com.revature.services.TransactionService;

@RestController
@RequestMapping(value="/transaction") // specfies the endpoint for the controller (transaction in this case)
@CrossOrigin(origins="http://localhost:4200/", allowCredentials="true")
public class TransactionController {
	
	private TransactionService tService;
	
	// inject the needed service class
	@Autowired
	public TransactionController(TransactionService tService) {
		this.tService = tService;
	}
	
	// get all transactions
	@GetMapping // method is called with a get request with the base endpoint (/transaction)
	public ResponseEntity<List<Transaction>> getAllTransactions() {
		// get the list of transactions from the service class
		List<Transaction> t = tService.getAllTransactions();
		
		// if the list is null, there was an error in getting the transactions from the database (db)
		if(t == null) {
			// send a response with a null body and the appropriate status code - no content code 204
			return ResponseEntity.status(204).body(null);
		}
		
		// the list was successfully retrieved from the db
		// set the body to the list and the status code to ok - 200
		return ResponseEntity.status(200).body(t);
	}
	
	// get a specific transaction based on id
	@GetMapping("/{id}")
	public ResponseEntity<Transaction> getTransactionById(@PathVariable int id) {
		// get a transaction based on a given id
		Transaction t = tService.getTransactionById(id);
		
		if(t == null) {
			return ResponseEntity.status(204).body(null);
		}
		
		return ResponseEntity.status(200).body(t);
	}
	
	// get transactions by account
	@GetMapping("/account/{account_id}")
	public ResponseEntity<List<Transaction>> getTransactionsByAccount(@PathVariable int account_id) {
		// get a list of transactions that belong to the account with the given id
		List<Transaction> t = tService.getTransactionsByAccount(account_id);
		
		if(t == null) {
			return ResponseEntity.status(204).body(null);
		}
		
		return ResponseEntity.status(200).body(t);
		
	}
	
	// get transactions by user
	@GetMapping("/user/{user_id}")
	public ResponseEntity<List<Transaction>> getTransactionsByUser(@PathVariable int user_id) {
		// get a list of transactions that belong to the user with the given id
		List<Transaction> t = tService.getTransactionsByUser(user_id);
		
		if(t == null) {
			return ResponseEntity.status(204).body(null);
		}
		
		return ResponseEntity.status(200).body(t);
	}
	
	// add a new transaction
	@PostMapping("/add/{account_id}")
	public ResponseEntity<Transaction> addTransaction(@RequestBody Transaction tran, @PathVariable int account_id) {
		// add the transaction to the account with the given id
		// returns the added transaction or null if unsuccessfull
		Transaction t = tService.addTransaction(tran, account_id);
		
		if(t == null) {
			return ResponseEntity.status(204).body(null);
		}
		
		return ResponseEntity.status(201).body(t);
	}
	
	// delete a transaction
	@DeleteMapping("/{id}")
	public ResponseEntity<Transaction> deleteTransaction(@PathVariable int id) {
		// delete the transaction with the given id
		// returns the deleted transaction or null if unsuccessful
		Transaction t = tService.deleteTransaction(id);
		
		if(t == null) {
			return ResponseEntity.status(204).body(null);
		}
		
		return ResponseEntity.status(200).body(t);
	}
	
	//Transfer money between two accounts
	//The TransferDTO class explains what the front end should send to this handler
	@PostMapping("/transfer/{username}")
	public ResponseEntity<Boolean> transferBetweenAccouunts(@RequestBody TransferDTO tdto, @PathVariable String username) {
		
		//First check to make sure the two accounts are unique
		if(tdto.getRecipientID()==tdto.getSenderID())
			return ResponseEntity.status(200).body(false);
		
		//Call the service layer to make the transfer
		boolean b = tService.transferFunds(tdto.getSenderID(), tdto.getRecipientID(), tdto.getAmount(), username);
		
		//b returns false if you attempt to transfer from an account that isn't yours
		if (b)
			return ResponseEntity.status(200).body(true);
		else
			return ResponseEntity.status(200).body(false);
		
	}
}
