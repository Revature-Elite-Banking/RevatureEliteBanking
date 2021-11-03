package com.revature.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.daos.TransactionRepository;
import com.revature.models.Transaction;

@Service
public class TransactionService {

	private TransactionRepository tDao;
	
	@Autowired
	public TransactionService(TransactionRepository tDao) {
		this.tDao = tDao;
	}
	
	public List<Transaction> getAllTransactions() {
		return tDao.findAll();
	}

	public Transaction getTransactionById(int id) {
		return tDao.getById(id);
	}

	public Transaction addTransaction(Transaction t) {
		return tDao.save(t);
	}

	public Transaction deleteTransaction(int id) {
		Transaction t = tDao.getById(id);
		tDao.delete(t);
		
		return t;
	}
}
