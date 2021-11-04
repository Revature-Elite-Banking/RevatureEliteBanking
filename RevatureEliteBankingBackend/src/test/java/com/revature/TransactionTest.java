package com.revature;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.revature.daos.AccountDAO;
import com.revature.daos.UserDAO;
import com.revature.enums.AccountType;
import com.revature.enums.TransactionStatus;
import com.revature.enums.TransactionType;
import com.revature.models.Account;
import com.revature.models.Transaction;
import com.revature.models.User;
import com.revature.services.TransactionService;

@SpringBootTest
@TestInstance(Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TransactionTest {

	private TransactionService tService;
	private AccountDAO aDao;
	private UserDAO uDao;
	
	@Autowired
	public TransactionTest(TransactionService tService, AccountDAO aDao, UserDAO uDao) {
		this.tService = tService;
		this.aDao = aDao;
		this.uDao = uDao;
	}
	
	@BeforeAll
	public void beforeTest() {
		List<Transaction> tlist = new ArrayList<>();
		List<Account> alist = new ArrayList<>();
		User u = new User("an", "password", "andies", "nunies", "email@email.com", "123 Main St", "NYC", "NY", "12345", alist);
		User returnedUser = uDao.save(u);
		
		User u2 = new User("am", "password", "audi", "mills", "gmail@email.com", "123 Main St", "NYC", "NY", "12345", alist);
		User returnedUser2 = uDao.save(u2);
		
		Account a = new Account(new Date(), 1000, uDao.findById(1).get(), AccountType.CHECKING, tlist);
		Account returnedAccount = aDao.save(a);
		
		Account a2 = new Account(new Date(), 800, uDao.findById(2).get(), AccountType.CHECKING, tlist);
		Account returnedAccount2 = aDao.save(a2);
		
		Account a3 = new Account(new Date(), 500, uDao.findById(1).get(), AccountType.SAVINGS, tlist);
		Account returnedAccount3 = aDao.save(a3);
	
		Transaction t = new Transaction(200, TransactionType.WITHDRAWL, new Date(), TransactionStatus.PENDING,
				"bought", aDao.findById(1).get());
		Transaction addedTransaction = tService.addTransaction(t);
		
		Transaction t2 = new Transaction(200, TransactionType.WITHDRAWL, new Date(), TransactionStatus.PENDING,
				"stuff", aDao.findById(2).get());
		Transaction addedTransaction2 = tService.addTransaction(t2);
		
		Transaction t3 = new Transaction(200, TransactionType.WITHDRAWL, new Date(), TransactionStatus.PENDING,
				"mooooooree", aDao.findById(3).get());
		Transaction addedTransaction3 = tService.addTransaction(t3);
	}
	
	@Test
	@Order(1)
	public void testAddTransaction() {
		Transaction t = new Transaction(200, TransactionType.WITHDRAWL, new Date(), TransactionStatus.PENDING,
				"bought dogecoin", aDao.findById(1).get());
		Transaction addedTransaction = tService.addTransaction(t);
		
		assertTrue(t.equals(addedTransaction));
	}
	
	@Test
	@Order(2)
	public void testGetAllTransactions() {
		List<Transaction> t = tService.getAllTransactions();
		assertTrue(t.size() == 4);
	}
	
	@Test
	@Order(3)
	public void testGetAccountTransactions() {
		List<Transaction> t = tService.getTransactionsByAccount(1);
		assertTrue(t.size() == 2);
	}
	
	@Test
	@Order(4)
	public void testGetUserTransactions() {
		List<Transaction> t = tService.getUserTransactionHistory(1);
		assertTrue(t.size() == 3);
	}
}
