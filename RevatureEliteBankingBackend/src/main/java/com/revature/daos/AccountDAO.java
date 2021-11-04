package com.revature.daos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.revature.models.Account;

@Repository
public interface AccountDAO extends JpaRepository<Account, Integer> {

}
