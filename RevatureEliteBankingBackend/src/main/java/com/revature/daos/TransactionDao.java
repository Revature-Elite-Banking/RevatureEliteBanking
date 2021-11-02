package com.revature.daos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.revature.models.Transaction;

//First parameter in <> is the class for the Dao, second is Integer for the primary key
public interface TransactionDao extends JpaRepository<Transaction, Integer>{ 

}
