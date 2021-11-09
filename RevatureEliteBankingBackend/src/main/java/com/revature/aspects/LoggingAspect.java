package com.revature.aspects;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.revature.models.Account;
import com.revature.models.Transaction;
import com.revature.models.User;


// All of the logging methods are kept in this file to keep them centralized and leaves less clutter throughout the rest of the application
// Implements Spring AOP (Aspect Oriented Programming)
@Component
@Aspect
public class LoggingAspect {

	private static Logger log = LogManager.getLogger(LoggingAspect.class);
	
	
	// ------------------------------------ Generic Before
	// logs before any method in the controller or service layer is called
	
	@Before("within(com.revature.services.*)")
	public void logServiceLayerBefore(JoinPoint jp) {
		log.debug("Before " + jp.getSignature() + " is called");
	}
	
	@Before("within(com.revature.controllers.*)")
	public void logControllerLayerBefore(JoinPoint jp) {
		log.debug("Before " + jp.getSignature() + " is called");
	}
	
	// --------------------------------------- Generic After Returning
	// logs after any method in the controller or service layer returns successfully 
	
	@AfterReturning(pointcut = "within(com.revature.services.*)", returning = "returnedObject")
	public void logServiceLayerAfter(JoinPoint jp, Object returnedObject) {
		log.debug("After " + jp.getSignature() + "." + jp.getSignature().getName() + " returned");
	}
	
	@AfterReturning(pointcut = "within(com.revature.controllers.*)", returning = "returnedObject")
	public void logAfterControllerLayerAfter(JoinPoint jp, Object returnedObject) {
		log.debug("After " + jp.getSignature() + "." + jp.getSignature().getName() + " returned");
	}
	
	// ----------------------------------------- Generic After Throwing
	// logs when any method in the controller or service layer throws an exception
	
	@AfterThrowing(pointcut = "within(com.revature.services.*)", throwing = "thrownException")
	public void logServiceLayerException(JoinPoint jp, Exception thrownException) {
		log.warn(jp.getSignature() + " threw an " + thrownException.toString() + "Exception");
	}
	
	@AfterThrowing(pointcut = "within(com.revature.controllers.*)", throwing = "thrownException")
	public void logControllerLayerException(JoinPoint jp, Exception thrownException) {
		log.warn(jp.getSignature() + " threw an " + thrownException.toString() + "Exception");
	}
	
	// ----------------------------------------- Transaction Service Specific
	// gives specific logs depending on the method in the service layer
	
	@AfterReturning(pointcut="execution(* com.revature.services.TransactionService.getAllTransactions(..))", returning = "returnedList")
	public void logGetAllTransactions(JoinPoint jp, List<Transaction> returnedList) {
		log.info("SUCCESS: GET REQUEST FOR ALL TRANSACTIONS");
	}
	
	@AfterThrowing(pointcut="execution(* com.revature.services.TransactionService.getAllTransactions(..))", throwing = "thrownException")
	public void logGetAllTransactionsException(JoinPoint jp, Exception thrownException) {
		log.warn("FAILED: GET REQUEST FOR ALL TRANSACTIONS");
	}
	
	@AfterReturning(pointcut="execution(* com.revature.services.TransactionService.getTransactionById(..))", returning = "returnedTransaction")
	public void logGetTransactionById(JoinPoint jp, Transaction returnedTransaction) {
		log.info("SUCCESS: GET REQUEST FOR TRANSACTION WITH ID = " + jp.getArgs()[0]);
	}
	
	@AfterThrowing(pointcut="execution(* com.revature.services.TransactionService.getAllTransactions(..))", throwing = "thrownException")
	public void logGetTransactionByIdException(JoinPoint jp, Exception thrownException) {
		log.warn("FAILED: GET REQUEST FOR TRANSACTION WITH ID = " + jp.getArgs()[0]);
	}
	
	@AfterReturning(pointcut="execution(* com.revature.services.TransactionService.addTransaction(..))", returning = "returnedTransaction")
	public void logAddTransaction(JoinPoint jp, Transaction returnedTransaction) {
		log.info("SUCCESS: POST REQUEST FOR ADDING TRANSACTION");
	}
	
	@AfterThrowing(pointcut="execution(* com.revature.services.TransactionService.addTransaction(..))", throwing = "thrownException")
	public void logAddTransactionException(JoinPoint jp, Exception thrownException) {
		log.warn("FAILED: POST REQUEST FOR ADDING TRANSACTION");
	}
	
	@AfterReturning(pointcut="execution(* com.revature.services.TransactionService.deleteTransaction(..))", returning = "deletedTransaction")
	public void logDeleteTransaction(JoinPoint jp, Transaction deletedTransaction) {
		log.info("SUCCESS: DELETE REQUEST FOR TRANSACTION WITH ID = " + jp.getArgs()[0]);
	}
	
	@AfterThrowing(pointcut="execution(* com.revature.services.TransactionService.deleteTransaction(..))", throwing = "thrownException")
	public void logDeleteTransactionException(JoinPoint jp, Exception thrownException) {
		log.warn("FAILED: DELETE REQUEST FOR TRANSACTION WITH ID = " + jp.getArgs()[0]);
	}
	
	@AfterReturning(pointcut="execution(* com.revature.services.TransactionService.getTransactionsByAccount(..))", returning = "returnedList")
	public void logGetTransactionsByAccount(JoinPoint jp, List<Transaction> returnedList) {
		log.info("SUCCESS: GET REQUEST FOR ALL TRANSACTIONS IN ACCOUNT WITH ID = " + jp.getArgs()[0]);
	}
	
	@AfterThrowing(pointcut="execution(* com.revature.services.TransactionService.getTransactionsByAccount(..))", throwing = "thrownException")
	public void logGetTransactionsByAccountException(JoinPoint jp, Exception thrownException) {
		log.warn("FAILED: GET REQUEST FOR ALL TRANSACTIONS IN ACCOUNT WITH ID = " + jp.getArgs()[0]);
	}
	
	@AfterReturning(pointcut="execution(* com.revature.services.TransactionService.getTransactionsByUser(..))", returning = "returnedList")
	public void logGetTransactionsByUser(JoinPoint jp, List<Transaction> returnedList) {
		log.info("SUCCESS: GET REQUEST FOR ALL TRANSACTIONS FOR USER WITH ID = " + jp.getArgs()[0]);
	}
	
	@AfterThrowing(pointcut="execution(* com.revature.services.TransactionService.getTransactionsByUser(..))", throwing = "thrownException")
	public void logGetTransactionsByUserException(JoinPoint jp, Exception thrownException) {
		log.warn("FAILED: GET REQUEST FOR ALL TRANSACTIONS FOR USER WITH ID = " + jp.getArgs()[0]);
	}
	
	// ----------------------------------------- Account Service Specific
	
	@AfterReturning(pointcut="execution(* com.revature.services.AccountService.getAccountsByUsername(..))", returning = "returnedList")
	public void logGetAccountsByUser(JoinPoint jp, List<Account> returnedList) {
		log.info("SUCCESS: GET REQUEST FOR ALL ACCOUNTS FOR USER WITH USERNAME = " + jp.getArgs()[0]);
	}
	
	@AfterThrowing(pointcut="execution(* com.revature.services.AccountService.getAccountsByUsername(..))", throwing = "thrownException")
	public void logGetAccountsByUserException(JoinPoint jp, Exception thrownException) {
		log.warn("FAILED: GET REQUEST FOR ALL ACCOUNTS FOR USER WITH USERNAME = " + jp.getArgs()[0]);
	}
	
	@AfterReturning(pointcut="execution(* com.revature.services.AccountService.newAccount(..))")
	public void logNewAccount(JoinPoint jp) {
		log.info("SUCCESS: POST REQUEST FOR ADDING A NEW ACCOUNT");
	}
	
	@AfterThrowing(pointcut="execution(* com.revature.services.AccountService.newAccount(..))", throwing = "thrownException")
	public void logNewAccountException(JoinPoint jp, Exception thrownException) {
		log.warn("FAILED: POST REQUEST FOR ADDING A NEW ACCOUNT");
	}
	
	// ----------------------------------------- Login Service Specific
	
	@AfterReturning(pointcut="execution(* com.revature.services.LoginService.checkCredentials(..))", returning ="returnedBool")
	public void logCheckCredentials(JoinPoint jp, boolean returnedBool) {
		if(returnedBool)
			log.info("SUCCESS: VALID CREDENTIALS GIVEN FOR USERNAME = " + jp.getArgs()[0]);
		else
			log.warn("FAILED: INVALID CREDENTIALS GIVEN FOR USERNAME = " + jp.getArgs()[0]);
	}
	
	@AfterThrowing(pointcut="execution(* com.revature.services.LoginService.checkCredentials(..))", throwing = "thrownException")
	public void logCheckCredentialsException(JoinPoint jp, Exception thrownException) {
		log.warn("FAILED: CHECK CREDENTIALS THREW AN EXCEPTION = " + thrownException.getClass());
	}
	
	// ----------------------------------------- User Login Detail Service Specific
	
	@AfterReturning(pointcut="execution(* com.revature.services.UserLoginDetailService.loadUserByUsername(..))", returning ="returnedObject")
	public void logLoadUserByUsername(JoinPoint jp, UserDetails returnedObject) {
		if(returnedObject != null)
			log.info("SUCCESS: USER LOADED WITH USERNAME = " + jp.getArgs()[0]);
		else
			log.warn("FAILED: USER WITH USERNAME = " + jp.getArgs()[0] + " NOT LOADED");
	}
	
	@AfterThrowing(pointcut="execution(* com.revature.services.UserLoginDetailService.loadUserByUsername(..))", throwing = "thrownException")
	public void logLoadUserByUsernameException(JoinPoint jp, Exception thrownException) {
		log.warn("FAILED: LOAD USER BY USERNAME = " + jp.getArgs()[0] + " THREW AN EXCEPTION = " + thrownException.getClass());
	}
	
	// ----------------------------------------- User Service Specific
	
	@AfterReturning(pointcut="execution(* com.revature.services.UserService.insertUser(..))", returning ="returnedObject")
	public void logInsertUser(JoinPoint jp, User returnedObject) {
		if(returnedObject != null)
			log.info("SUCCESS: POST REQUEST TO ADD USER");
		else
			log.warn("FAILED: POST REQUEST TO ADD USER");
	}
	
	@AfterThrowing(pointcut="execution(* com.revature.services.UserService.insertUser(..))", throwing = "thrownException")
	public void logInsertUserException(JoinPoint jp, Exception thrownException) {
		log.warn("FAILED: POST REQUEST TO ADD USER THREW AN EXCEPTION = " + thrownException.getClass());
	}
	
	@AfterReturning(pointcut="execution(* com.revature.services.UserService.findUserAll(..))", returning ="returnedList")
	public void logFindUserAll(JoinPoint jp, List<User> returnedList) {
		if(returnedList != null)
			log.info("SUCCESS: RETURNED A LIST OF ALL USERS");
		else
			log.warn("FAILED: LIST OF ALL USERS = NULL");
	}
	
	@AfterThrowing(pointcut="execution(* com.revature.services.UserService.findUserAll(..))", throwing = "thrownException")
	public void logFindUserAllException(JoinPoint jp, Exception thrownException) {
		log.warn("FAILED: FIND USER ALL THREW AN EXCEPTION = " + thrownException.getClass());
	}
	
	@AfterReturning(pointcut="execution(* com.revature.services.UserService.findUserByUsername(..))", returning ="returnedObject")
	public void logFindUserByUsername(JoinPoint jp, User returnedObject) {
		if(returnedObject != null)
			log.info("SUCCESS: FOUND USER WITH USERNAME = " + jp.getArgs()[0]);
		else
			log.warn("FAILED: USER WITH USERNAME = " + jp.getArgs()[0] + " NOT FOUND");
	}
	
	@AfterThrowing(pointcut="execution(* com.revature.services.UserService.findUserByUsername(..))", throwing = "thrownException")
	public void logFindUserByUsernameException(JoinPoint jp, Exception thrownException) {
		log.warn("FAILED: FIND USER BY USERNAMEL THREW AN EXCEPTION = " + thrownException.getClass());
	}
}
