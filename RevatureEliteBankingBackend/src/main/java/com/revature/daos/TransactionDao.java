package com.revature.daos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.revature.models.Transaction;

//First parameter in <> is the class for the Dao, second is Integer for the primary key
@Repository
public interface TransactionDao extends JpaRepository<Transaction, Integer>{ 

}
