package com.revature.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.daos.AccountDAO;
import com.revature.daos.TransactionRepository;
import com.revature.daos.UserDAO;
import com.revature.enums.TransactionType;
import com.revature.models.Account;
import com.revature.models.Transaction;
import com.revature.models.User;

@Service
public class AccountService {

	private TransactionRepository tDao;
	private UserDAO uDao;
	private AccountDAO aDao;
	
	@Autowired
	public AccountService(TransactionRepository tDao, UserDAO uDao, AccountDAO aDao) {
		this.tDao = tDao;
		this.uDao = uDao;
		this.aDao = aDao;
	}
	
	public Optional<List<Account>> getAccountsByUsername(String username) {
		User user = uDao.findByUsername(username);
		return aDao.findByUser(user);
	}
	
	public void newAccount(String username, Account account) {
		//Adding default values to the new account
		account.setUser(uDao.findByUsername(username));
		
		Date date = new Date();
		account.setCreationTime(date);
		
		aDao.save(account);
		
		//Transaction is created with an initial deposit
		Transaction tran = new Transaction(account.getBalance(), TransactionType.DEPOSIT, date, "Account creation and initial deposit", account);
		tDao.save(tran);
		
		
	}
}
