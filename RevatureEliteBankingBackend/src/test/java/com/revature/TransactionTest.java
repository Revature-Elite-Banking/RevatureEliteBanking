package com.revature;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.revature.daos.AccountDAO;
import com.revature.daos.UserDAO;
import com.revature.enums.TransactionStatus;
import com.revature.enums.TransactionType;
import com.revature.models.Transaction;
import com.revature.services.TransactionService;

@SpringBootTest
@TestInstance(Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TransactionTest {

	private TransactionService tService;
	private AccountDAO aDao;
	private UserDAO uDao;
	private int lastTransactionId;
	
	// inject the necessary classes so the tests can run
	// AccountDAO and UserDAO only used if creating the test data in beforeTest()
	@Autowired
	public TransactionTest(TransactionService tService, AccountDAO aDao, UserDAO uDao) {
		this.tService = tService;
		this.aDao = aDao;
		this.uDao = uDao;
	}
	
	@BeforeAll
	public void beforeTest() {
		/* If the database is empty, uncomment this to set it up with test data
		// this test data assumes that the database is empty

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
		
		Transaction t3 = new Transaction(1212, TransactionType.WITHDRAWL, new Date(), TransactionStatus.PENDING,
				"mooooooree", aDao.findById(3).get());
		Transaction addedTransaction3 = tService.addTransaction(t3);
		//*/
	}
	
	@Test
	@DisplayName("Adding a transaction") // changes the text for the test description
	@Order(1) // defines in what order the test will be executed - 1 is the first test
	public void testAddTransaction() {
		double preTranBalance = aDao.findById(1).get().getBalance();
		
		Transaction t = new Transaction(800, TransactionType.WITHDRAWL, null, TransactionStatus.PENDING,
				"bought more dogecoin", null);
		Transaction addedTransaction = tService.addTransaction(t, 1);
		
		// check the returned transaction is equal to the one we created
		assertTrue(t.equals(addedTransaction));
		
		// the new balance should equal the old balance minus the transaction amount
		assertTrue(preTranBalance - addedTransaction.getAmount() == aDao.findById(1).get().getBalance());
		
		// store the id of this transaction for the next test
		lastTransactionId = addedTransaction.getId();
	}
	
	@Test
	@DisplayName("Deleting a transaction (deletes the newly created transaction)")
	@Order(2)
	public void testDeleteTransaction() {
		// get the current balance for the account
		double preDeleteBalance = aDao.findById(1).get().getBalance();
		
		// deletes the transaction that was added from the first test
		Transaction deletedTransaction = tService.deleteTransaction(lastTransactionId);
		
		// check if the transaction is null and that the id is the same
		assertTrue(deletedTransaction != null);
		assertTrue(deletedTransaction.getId() == lastTransactionId);
		
		// the new balance should be the same as pre-test1
		// (since the transaction is being deleted the opposite happens to the balance)
		assertTrue(preDeleteBalance + deletedTransaction.getAmount() == aDao.findById(1).get().getBalance());
	}
	
	@Test
	@DisplayName("Getting all transactions in the database")
	@Order(3)
	public void testGetAllTransactions() {
		List<Transaction> t = tService.getAllTransactions();
		
		// the list is the correct size
		assertTrue(t.size() == 3);
	}
	
	@Test
	@DisplayName("Getting all transactions in the same account")
	@Order(4)
	public void testGetAccountTransactions() {
		List<Transaction> t = tService.getTransactionsByAccount(1);
		
		// check that the list is the correct size
		assertTrue(t.size() == 1);
		
		// check that each transaction belongs to the same account
		for(Transaction T : t) {
			assertTrue(T.getAccount().getId() == 1);
		}
	}
	
	@Test
	@DisplayName("Getting all transactions for the same user")
	@Order(5)
	public void testGetUserTransactions() {
		List<Transaction> t = tService.getTransactionsByUser(1);
		
		// check that the list is the correct size
		assertTrue(t.size() == 2);
		
		// each transaction in the list should be associated with an account that is owned by a user with the same id
		for(Transaction T : t) {
			assertTrue(T.getAccount().getUser().getId() == 1);
		}
	}
	
	@Test
	@DisplayName("Getting a transaction by its id")
	@Order(6)
	public void testGetTransactionById() throws Exception{
		Transaction t = tService.getTransactionById(3);
		
		// Date checks are annoying when the formats are different so its the only field that is not checked
		// Realistically, if the id of the returned transaction is correct, then it's the right transaction
		
		assertTrue(t != null);
		assertTrue(t.getAmount() == 1212);
		assertTrue(t.getAccount().getId() == 3);
		assertTrue(t.getId() == 3);
		assertTrue(t.getDescription().equals("mooooooree"));
		assertTrue(t.getStatus() == TransactionStatus.PENDING);
		assertTrue(t.getType() == TransactionType.WITHDRAWL);
	}
}
