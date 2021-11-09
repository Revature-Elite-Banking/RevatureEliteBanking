package com.revature.services;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.daos.AccountDAO;
import com.revature.daos.TransactionRepository;
import com.revature.daos.UserDAO;
import com.revature.enums.TransactionType;
import com.revature.models.Account;
import com.revature.models.Transaction;
import com.revature.models.User;

// Service Layer for Transactions
@Service
public class TransactionService {

	private TransactionRepository tDao;
	private UserDAO uDao;
	private AccountDAO aDao;

	// Injecting the DAOs needed to access different tables from the database
	@Autowired
	public TransactionService(TransactionRepository tDao, UserDAO uDao, AccountDAO aDao) {
		this.tDao = tDao;
		this.uDao = uDao;
		this.aDao = aDao;
	}
	
	public List<Transaction> getAllTransactions() {
		// try catch blocks in case an exception occurs while accessing the database
		// if an exception occurs, a null object is returned
		try {
			return tDao.findAll();
		}
		catch(Exception e) {
			return null;
		}
	}

	public Transaction getTransactionById(int id) {
		try {
			return tDao.findById(id).get();
		}
		catch (Exception e) {
			return null;
		}
	}

	public Transaction addTransaction(Transaction tran, int account_id) {
		try {
			// get the account with the given id
			Account a = aDao.findById(account_id).get();
			
			// set the date and account for the new transaction
			tran.setAccount(a);
			tran.setDate(new Date());
			
			// save the new transaction to the database
			Transaction t = tDao.save(tran);
			
			// if the transaction is a deposit then add money to the account
			if(t.getType() == TransactionType.DEPOSIT) {
				a.setBalance(a.getBalance() + t.getAmount());
			}
			// any other type of transaction takes money out of the account
			else {
				a.setBalance(a.getBalance() - t.getAmount());
			}
			
			// save the changes to the account
			a = aDao.save(a);
			
			// return the added transaction
			return t;
		}
		catch (Exception e) {
			return null;
		}
	}

	public Transaction deleteTransaction(int id) {
		try {
			// get the transaction from the id and delete it from the db
			Transaction t = tDao.findById(id).get();
			tDao.delete(t);
			
			// get the account mapped to the transaction
			Account a = t.getAccount();
			
			// if a transaction is being deleted it should be reflected in the account's balance
			// Deposits = take money out, Withdraw/Transfer = put money back in
			if(t.getType() == TransactionType.DEPOSIT) {
				a.setBalance(a.getBalance() - t.getAmount());
			}
			else {
				a.setBalance(a.getBalance() + t.getAmount());
			}
			
			// save the changes to the account
			aDao.save(a);
			
			// return the deleted account
			return t;
		}
		catch (Exception e) {
			return null;
		}
	}

	public List<Transaction> getTransactionsByAccount(int account_id) {
		try {
			// get the account based on the id
			Account a = aDao.findById(account_id).get();
			
			// get transactions that belong to the account
			List<Transaction> t = tDao.findByAccount(a);

			// return the list of transactions
			return t;
		}
		catch (Exception e) {
			return null;
		}
		
	}

	public List<Transaction> getTransactionsByUser(int user_id) {
		try {
			// get the user based on the given id
			User u = uDao.findById(user_id).get();
			
			// get a list of transactions that belong to the given user
			List<Transaction> t = tDao.findUserTransactionHistory(u);
			
			// return the list of transactions
			return t;
		}
		catch (Exception e) {
			return null;
		}
	}
}
