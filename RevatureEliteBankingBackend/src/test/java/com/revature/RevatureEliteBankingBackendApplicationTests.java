package com.revature;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.revature.enums.AccountType;
import com.revature.models.Account;
import com.revature.models.Transaction;
import com.revature.models.User;
import com.revature.services.LoginService;
import com.revature.services.UserLoginDetailService;
import com.revature.services.UserService;

@SpringBootTest
class RevatureEliteBankingBackendApplicationTests {

	//List of all services to test
	private LoginService ls;
	private UserLoginDetailService ulds;
	private UserService us;
	
	//Autowiring service classes
	@Autowired
	public RevatureEliteBankingBackendApplicationTests(UserLoginDetailService ulds, UserService us, LoginService ls) {
		super();
		this.ulds = ulds;
		this.us = us;
		this.ls = ls;
	}
	
	//variables and objects to use within tests (Left out creation time due to Date not being able to be called statically
	public static User u = new User();
	public static Account a = new Account();
	public static List<Transaction> transactions = null;
	public static AccountType type = AccountType.CHECKING;
	public boolean result;
	
	//Set fields here so which test runs first does not matter
	//(Did not set creationTime due to Date not being able to be called statically
	@BeforeAll
	public static void createServices() {
		
		u.setId(1);
		u.setUsername("test1");
		u.setPassword("12345");
		u.setFirstName("Nabin");
		u.setLastName("Khatri");
		u.setEmail("test1@user.com");
		u.setAddress("253 shoreside dr");
		u.setCity("Lexington");
		u.setState("KY");
		u.setZipCode("40511");
		
		a.setId(1);
		a.setBalance(0);
		a.setUser(u);
		a.setType(type);
		a.setTransactions(transactions);
		
	}
	
	@AfterAll
	public static void clearServices() {
		u = null;
		a = null;
	}
	
	//Tests to follow below
	//Test to check that correct username returns true
	@Test
	public void testLoginSuccess() {
		
		result = ls.checkCredentials(u.getUsername(), u.getPassword());
		
		assertTrue(result);
	}
	
	//Test to check that incorrect username returns false
	@Test
	public void testLoginFail() {
		
		result = ls.checkCredentials("fake", "asd");
		
		assertFalse(result);
	}
}
