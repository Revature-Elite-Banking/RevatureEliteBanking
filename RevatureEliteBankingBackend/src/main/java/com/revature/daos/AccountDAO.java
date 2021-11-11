package com.revature.daos;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.revature.models.Account;
import com.revature.models.User;

@Repository
public interface AccountDAO extends JpaRepository<Account, Integer> {
	
	//That query ensures that the returned list is ordered by account ID
	//If you remove it every update to the accounts table is gonna change the order displayed on the front
	@Query("FROM Account WHERE user=?1 ORDER BY id")
	public Optional<List<Account>> findByUser(User user);
}
