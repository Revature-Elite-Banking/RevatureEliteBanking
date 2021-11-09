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

	public Transaction addTransaction(Transaction tran, int account_id) {
		try {
			// get the account mapped to the id
			Account a = aDao.findById(account_id).get();
			
			// set the account for the new transaction
			tran.setAccount(a);
			tran.setDate(new Date());
			//As of now all transactions default to completed
			//This probably won't change unless actual payment services are implemented
			tran.setStatus(TransactionStatus.COMPLETED);
			
			Transaction t = tDao.save(tran);
			
			// if a deposit then add money to the account
			if(t.getType() == TransactionType.DEPOSIT) {
				a.setBalance(a.getBalance() + t.getAmount());
			}
			// any other type of transaction takes money out of the account
			else {
				a.setBalance(a.getBalance() - t.getAmount());
			}
			
			// save the changes to the account
			a = aDao.save(a);
			
			return t;
		}
		catch (Exception e) {
			System.out.println("Failed to add a transaction: " + tran);
			return null;
		}
	}

	public Transaction deleteTransaction(int id) {
		try {
			// get the transaction from the id and delete it
			Transaction t = tDao.findById(id).get();
			tDao.delete(t);
			
			// get the account mapped to the transaction
			Account a = t.getAccount();
			
			// if a transaction is being deleted (if it fails) it should be reflected in the account's balance
			// Deposits = take money out, Withdraw/Transfer = put money back in
			if(t.getType() == TransactionType.DEPOSIT) {
				a.setBalance(a.getBalance() - t.getAmount());
			}
			else {
				a.setBalance(a.getBalance() + t.getAmount());
			}
			
			// save the changes to the account
			aDao.save(a);
			
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
	
	public void transferFunds (Account sender, Account recipient, double amount) {
		//Update the account balances
		double senderTotal = sender.getBalance()-amount;
		double recipientTotal = recipient.getBalance()+amount;
		
		sender.setBalance(senderTotal);
		recipient.setBalance(recipientTotal);
		
		aDao.save(sender);
		aDao.save(recipient);
		
		
		//Create two transaction objects
		Date date = new Date();
		double amountOut = amount*-1;
		
		Transaction moneySent = new Transaction(amountOut, TransactionType.TRANSFEROUT, date, "Transfer to account #"+recipient.getId(), sender);
		Transaction moneyRecieved = new Transaction(amount, TransactionType.TRANSFERIN, date, "Transfer from account #"+sender.getId(), recipient);
		
		tDao.save(moneySent);
		tDao.save(moneyRecieved);
		
		
	}
}
