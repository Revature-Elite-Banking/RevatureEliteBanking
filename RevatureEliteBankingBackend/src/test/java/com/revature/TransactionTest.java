package com.revature;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Date;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.revature.enums.TransactionStatus;
import com.revature.enums.TransactionType;
import com.revature.models.Transaction;
import com.revature.services.TransactionService;

@SpringBootTest
public class TransactionTest {

	private TransactionService tService;
	
	@Autowired
	public TransactionTest(TransactionService tService) {
		this.tService = tService;
	}
	
	@Test
	public void testAddTransaction() {
		Transaction t = new Transaction();
		t.setAmount(200);
		Date date = new Date();
		t.setDate(date);
		t.setDescription("bought dogecoin");
		t.setId(0);
		t.setStatus(TransactionStatus.PENDING);
		t.setType(TransactionType.WITHDRAWL);
		
		tService.addTransaction(t);
		
		assertTrue(tService.getTransactionById(0).equals(t));
	}
}
