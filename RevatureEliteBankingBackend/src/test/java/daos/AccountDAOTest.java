/*
 * package daos;
 * 
 * import static org.junit.jupiter.api.Assertions.assertEquals;
 * 
 * import java.sql.Timestamp; import java.util.ArrayList; import java.util.Date;
 * import java.util.List; import java.util.Optional;
 * 
 * import org.junit.jupiter.api.DisplayName; import org.junit.jupiter.api.Test;
 * import org.springframework.beans.factory.annotation.Autowired; import
 * org.springframework.data.domain.Example; import
 * org.springframework.data.domain.Page; import
 * org.springframework.data.domain.Pageable; import
 * org.springframework.data.domain.Sort;
 * 
 * import com.revature.daos.AccountDAO; import com.revature.enums.AccountType;
 * import com.revature.models.Account; import com.revature.models.Transaction;
 * 
 * import org.springframework.data.jpa.repository.JpaRepository;
 * 
 * public class AccountDAOTest {
 * 
 * Date date = new Date(); long time = date.getTime(); Timestamp tstmp = new
 * Timestamp(time); ArrayList<Transaction> blank = new ArrayList<Transaction>();
 * 
 * double startingBalance = 0;
 * 
 * AccountType accountType = AccountType.CHECKING;
 * 
 * private AccountDAO aDAO;
 * 
 * @Autowired public AccountDAOTest(AccountDAO aDAO) { super(); this.aDAO =
 * aDAO; }
 * 
 * 
 * Account account = new Account(tstmp, startingBalance, 2, accountType, blank);
 * 
 * @Test
 * 
 * @DisplayName("Attempt to put an account in the database then retrieve it")
 * void testNewAccountCreation() { aDAO.save(account);
 * 
 * assertEquals(account, aDAO.findById(account.getId())); }
 * 
 * 
 * 
 * }
 */