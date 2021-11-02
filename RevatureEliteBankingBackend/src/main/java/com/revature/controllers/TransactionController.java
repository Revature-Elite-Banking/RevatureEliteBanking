package com.revature.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.daos.TransactionDao;
import com.revature.models.Transaction;

@RestController // combines @Controller and @ResponseBody, looks cleaner
@RequestMapping(value="/transaction") // specfies the endpoint for the controller (transactions in this case)
public class TransactionController {
	
	private TransactionDao tDao;
	
	@Autowired
	TransactionController(TransactionDao tDao) {
		this.tDao = tDao;
	}
	
	// get all transactions
	// GetMapping means this method will be called for a Get request
	@GetMapping
	public ResponseEntity<List<Transaction>> getAllTransactions() {
		List<Transaction> t = tDao.findAll();
		
		return ResponseEntity.status(200).body(t);
	}
	
	/*
	@PostMapping("/add") // -- /transaction/add
	public ResponseEntity<Transaction> addTransaction(@RequestBody Transaction t) {
		tDao.save(t);
		
		return ResponseEntity.status(200).body(t);
	}
	//*/
}
