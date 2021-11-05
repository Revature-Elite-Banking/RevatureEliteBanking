package com.revature.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.revature.daos.AccountDAO;
import com.revature.daos.TransactionRepository;
import com.revature.daos.UserDAO;
import com.revature.models.Account;
import com.revature.models.User;

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
}
