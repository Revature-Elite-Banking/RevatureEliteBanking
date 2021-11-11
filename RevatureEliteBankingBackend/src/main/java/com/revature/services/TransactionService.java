package com.revature.services;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.daos.AccountDAO;
import com.revature.daos.TransactionRepository;
import com.revature.daos.UserDAO;
import com.revature.enums.TransactionStatus;
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
			//As of now all transactions default to completed
			//This probably won't change unless actual payment services are implemented
			tran.setStatus(TransactionStatus.COMPLETED);
			
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
	
	public boolean transferFunds (int senderID, int recipientID, double amount, String username) {
		//First check to make sure both accounts are owned by the person making the transfer
		if (aDao.getById(senderID).getUser().getUsername()!=username||aDao.getById(recipientID).getUser().getUsername()!=username) {
			
			Account sender = aDao.getById(senderID);
			Account recipient = aDao.getById(recipientID);
		
			double senderTotal = sender.getBalance()-amount;
			double recipientTotal = recipient.getBalance()+amount;
			//Set the balances of the two accounts to account for the transfer
			sender.setBalance(senderTotal);
			recipient.setBalance(recipientTotal);
		
			aDao.save(sender);
			aDao.save(recipient);
		
		
			//Create two transaction objects
			//Both will have a positive amount, with one being an incoming transfer and the other being an outgoing transfer
			Date date = new Date();
		
			Transaction moneySent = new Transaction(amount, TransactionType.TRANSFEROUT, date, "Transfer to account #"+recipient.getId(), sender);
			Transaction moneyRecieved = new Transaction(amount, TransactionType.TRANSFERIN, date, "Transfer from account #"+sender.getId(), recipient);
		
			tDao.save(moneySent);
			tDao.save(moneyRecieved);
			
			return true;
		}
		//The bool returned tells the controller layer if the transfer is authorized
		else
			return false;
		
		
	}
}
