package tests;

import static org.junit.Assert.assertEquals;

import java.util.List;

import javax.sql.DataSource;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import daoLayer.AccountDAO;
import daoLayer.UsersDAO;
import domainLayer.Account;
import domainLayer.User;

@ActiveProfiles("dev")
@ContextConfiguration(locations = { "file:src/main/java/config/security-context.xml",
		"file:src/test/java/testConfig/datasource.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
public class AccountDAOTests {

	@Autowired
	private UsersDAO usersDAO;

	@Autowired
	private AccountDAO accountDAO;
	

	@Autowired
	private DataSource dataSource;

	User user1 = new User("user1", "asdsa", "dasd", 123, "sda", "qweqw", true);
	User user2 = new User("user2", "asdsa", "dasd", 123, "sda", "qweqw", true);
	User user3 = new User("user3", "asdsa", "dasd", 123, "sda", "qweqw", true);

	@After
	@Before
	public void init() {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

		jdbcTemplate.execute("DELETE FROM bills");
		jdbcTemplate.execute("DELETE FROM transactions");
		jdbcTemplate.execute("DELETE FROM accounts");
		jdbcTemplate.execute("DELETE FROM users");
	}

	@Test
	public void testCreateRetrieve() {
		usersDAO.saveUser(user1);
		usersDAO.saveUser(user2);
		usersDAO.saveUser(user3);

		Account acc1 = new Account(100, user1);
		Account acc2 = new Account(200, user1);
		Account acc3 = new Account(300, user1);
		Account acc4 = new Account(15, user2);
		Account acc5 = new Account(20, user2);
		Account acc6 = new Account(555, user3);

		accountDAO.saveOrUpdateAccount(acc1);
		accountDAO.saveOrUpdateAccount(acc2);
		accountDAO.saveOrUpdateAccount(acc3);
		accountDAO.saveOrUpdateAccount(acc4);
		accountDAO.saveOrUpdateAccount(acc5);
		accountDAO.saveOrUpdateAccount(acc6);

		List<Account> accountList = accountDAO.getAllAccounts();

		assertEquals("Should be 6", 6, accountList.size());

		List<Account> accountListPerUser = accountDAO.getAllAccountsForUser(user2);
		assertEquals("Offer should match with the initial one", 2, accountListPerUser.size());

	}

	@Test
	public void testDeleteAccounts(){
		usersDAO.saveUser(user1);
		usersDAO.saveUser(user2);
		
		Account acc1 = new Account(100, user1);
		Account acc2 = new Account(200, user2);
		
		accountDAO.saveOrUpdateAccount(acc1);
		accountDAO.saveOrUpdateAccount(acc2);
			
		accountDAO.deleteAccount(acc1);
		accountDAO.deleteAccount(acc2);
		
		List<Account> offerList=accountDAO.getAllAccounts();
		assertEquals("There should be no users",0,offerList.size());
		
	}
	
	@Test
	public void testUpdateAccounts(){
		usersDAO.saveUser(user1);
		usersDAO.saveUser(user2);
		
		Account acc1 = new Account(100, user1);
		Account acc2 = new Account(200, user2);
		
		accountDAO.saveOrUpdateAccount(acc1);
		accountDAO.saveOrUpdateAccount(acc2);
			
		acc1.setMoneyAmount(500);
		acc2.setMoneyAmount(1000);
		
		accountDAO.saveOrUpdateAccount(acc1);
		accountDAO.saveOrUpdateAccount(acc2);
		
		
		List<Account> accountList1 = accountDAO.getAllAccountsForUser(user1);
		List<Account> accountList2 = accountDAO.getAllAccountsForUser(user2);

		assertEquals(accountList1.get(0).getMoneyAmount(), 500);
		assertEquals(accountList2.get(0).getMoneyAmount(), 1000);
		
	}
	
	@Test
	public void testGetById(){
		usersDAO.saveUser(user1);
		usersDAO.saveUser(user2);
		
		Account acc1 = new Account(100, user1);
		Account acc2 = new Account(100, user1);
		Account acc3 = new Account(100, user2);
		
		accountDAO.saveOrUpdateAccount(acc1);
		accountDAO.saveOrUpdateAccount(acc2);
		accountDAO.saveOrUpdateAccount(acc3);
		
		List<Account> allAccountsForUser = accountDAO.getAllAccountsForUser(user1);
		
		assertEquals(allAccountsForUser.size(), 2);
	}

}