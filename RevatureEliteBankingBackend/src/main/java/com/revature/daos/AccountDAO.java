package com.revature.daos;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.revature.models.Account;
import com.revature.models.User;

@Repository
public interface AccountDAO extends JpaRepository<Account, Integer> {
	
	public Optional<List<Account>> findByUser(User user);
}
