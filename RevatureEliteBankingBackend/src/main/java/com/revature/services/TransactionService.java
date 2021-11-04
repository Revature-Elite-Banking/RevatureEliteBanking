package com.revature.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.daos.AccountDAO;
import com.revature.daos.TransactionRepository;
import com.revature.daos.UserDAO;
import com.revature.models.Account;
import com.revature.models.Transaction;
import com.revature.models.User;

@Service
public class TransactionService {

	private TransactionRepository tDao;
	private UserDAO uDao;
	private AccountDAO aDao;
	
	@Autowired
	public TransactionService(TransactionRepository tDao, UserDAO uDao, AccountDAO aDao) {
		this.tDao = tDao;
		this.uDao = uDao;
		this.aDao = aDao;
	}
	
	public List<Transaction> getAllTransactions() {
		return tDao.findAll();
	}

	public Transaction getTransactionById(int id) {
		return tDao.findById(id).get();
	}

	public Transaction addTransaction(Transaction tran) {
		Transaction t = tDao.save(tran);
		return t;
	}

	public Transaction deleteTransaction(int id) {
		Transaction t = tDao.findById(id).get();
		tDao.delete(t);
		
		return t;
	}

	public List<Transaction> getTransactionsByAccount(int account_id) {
		Account a = aDao.findById(account_id).get();
		List<Transaction> t = tDao.findByAccount(a);

		return t;
	}

	public List<Transaction> getUserTransactionHistory(int user_id) {
		User u = uDao.findById(user_id).get();
		List<Transaction> t = tDao.findUserTransactionHistory(u);
		
		return t;
	}
}
