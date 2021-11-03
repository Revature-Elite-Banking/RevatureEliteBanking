package daos;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.revature.daos.AccountDAO;
import com.revature.enums.AccountType;
import com.revature.models.Account;
import com.revature.models.Transaction;

import org.springframework.data.jpa.repository.JpaRepository;


public class AccountDAOTest {
	
	private AccountDAO aDAO;
	
	@Autowired
	public AccountDAOTest(AccountDAO aDAO) {
		super();
		this.aDAO = aDAO;
	}
	
	public static Account account = new Account();
	public static Date date = new Date();
	public static long time = date.getTime();
	public static Timestamp tstmp = new Timestamp(time);
	public static ArrayList<Transaction> blank = new ArrayList<Transaction>();
	public static double startingBalance = 0;
	
	public static AccountType accountType = AccountType.CHECKING;
	
	@BeforeAll
	public static void createAccount() {
		account.setBalance(startingBalance);
		account.setCreationTime(tstmp);
		account.setType(accountType);
		account.setTransactions(blank);
		}
	
	@Test
	@DisplayName("Attempt to put an account in the database then retrieve it")
	void testNewAccountCreation() {
		aDAO.save(account);
		
		assertEquals(account, aDAO.findById(account.getId()));
	}

	
	
}
