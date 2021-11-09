package com.revature.daos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.revature.models.Account;
import com.revature.models.Transaction;
import com.revature.models.User;

//First parameter in <> is the class for the Dao, second is Integer for the primary key
@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Integer>{ 
	
	// Spring Data makes the query based off the method name - returns a List of Transactions by the given Account
	@Query("FROM Transaction t WHERE t.account = ?1 ORDER BY t.id")
	public List<Transaction> findByAccount(Account account);
	
	// the query in parenthesis returns a set of accounts with the given user
	// the first part of the query returns all transactions based off the second query's set of accounts
	@Query("FROM Transaction t WHERE t.account IN (FROM Account a WHERE a.user =?1) ORDER BY t.id")
	public List<Transaction> findUserTransactionHistory(User user);
}
