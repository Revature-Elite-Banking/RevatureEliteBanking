package com.revature.services;

import java.util.Date;
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
		// try catch blocks in case an exception occurs while accessing the database
		try {
			return tDao.findAll();
		}
		catch(Exception e) {
			System.out.println("Failed to get all transactions");
			return null;
		}
	}

	public Transaction getTransactionById(int id) {
		try {
			return tDao.findById(id).get();
		}
		catch (Exception e) {
			System.out.println("Failed to get a transaction with id = " + id);
			return null;
		}
	}

	public Transaction addTransaction(Transaction tran) {
		try {
			/* test 
			tran.setAccount(aDao.findById(1).get());
			tran.setDate(new Date());
			
			//*/
			Transaction t = tDao.save(tran);
			return t;
		}
		catch (Exception e) {
			System.out.println("Failed to add a transaction: " + tran);
			return null;
		}
	}

	public Transaction deleteTransaction(int id) {
		try {
			Transaction t = tDao.findById(id).get();
			tDao.delete(t);
			
			return t;
		}
		catch (Exception e) {
			System.out.println("Failed to delete a transaction with id = " + id);
			return null;
		}
	}

	public List<Transaction> getTransactionsByAccount(int account_id) {
		try {
			Account a = aDao.findById(account_id).get();
			List<Transaction> t = tDao.findByAccount(a);

			return t;
		}
		catch (Exception e) {
			System.out.println("Failed to get transactions from account with id = " + account_id);
			return null;
		}
		
	}

	public List<Transaction> getUserTransactionHistory(int user_id) {
		try {
			User u = uDao.findById(user_id).get();
			List<Transaction> t = tDao.findUserTransactionHistory(u);
			return t;
		}
		catch (Exception e) {
			System.out.println("Failed to get transaction history for user with id = " + user_id);
			return null;
		}
	}
}
