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
import daoLayer.TransactionDAO;
import daoLayer.UsersDAO;
import domainLayer.Account;
import domainLayer.Transaction;
import domainLayer.User;
import serviceLayer.AccountsService;
import serviceLayer.UsersService;

@ActiveProfiles("dev")
@ContextConfiguration(locations = { "file:src/main/java/config/security-context.xml",
		"file:src/test/java/testConfig/datasource.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
public class TransactionDAOTests {

	@Autowired
	private UsersDAO usersDAO;

	@Autowired
	private AccountDAO accountDAO;

	@Autowired
	private TransactionDAO transactionDAO;
	
	@Autowired
	private UsersService usersService;
	
	
	@Autowired
	private DataSource dataSource;

	User user1 = new User("mihai", "test", "Mihai Pop", 19475725, "Dorobantilor 109", "ROLE_USER", true);
	User user2 = new User("admin", "test", "Andreea Muresan", 19357621, "Ceahlau 14", "ROLE_ADMIN", true);
	User user3 = new User("bogdan", "test", "Bogdan Gliga", 19958692, "Giulesti 10", "ROLE_USER", true);


	@Before
	public void init() {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

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
		Account acc3 = new Account(300, user2);
		Account acc4 = new Account(15, user2);
		Account acc5 = new Account(20, user3);
		Account acc6 = new Account(555, user3);

		accountDAO.saveOrUpdateAccount(acc1);
		accountDAO.saveOrUpdateAccount(acc2);
		accountDAO.saveOrUpdateAccount(acc3);
		accountDAO.saveOrUpdateAccount(acc4);
		accountDAO.saveOrUpdateAccount(acc5);
		accountDAO.saveOrUpdateAccount(acc6);
				
		accountDAO.transferMoney(acc1, acc2, 543);
		accountDAO.transferMoney(acc2, acc4, 7);
		accountDAO.transferMoney(acc5, acc6, 5);
		accountDAO.transferMoney(acc6, acc1, 4);
		accountDAO.transferMoney(acc2, acc1, 5);
		accountDAO.transferMoney(acc2, acc1, 77);
		accountDAO.transferMoney(acc6, acc2, 33);
		accountDAO.transferMoney(acc5, acc3, 66);
		accountDAO.transferMoney(acc5, acc6, 5);
		accountDAO.transferMoney(acc2, acc5, 77);
		accountDAO.transferMoney(acc4, acc1, 8);
		accountDAO.transferMoney(acc3, acc2, 2);
		
		List<Transaction> u1Tran = transactionDAO.getAllTransactions(user1,0,0);
		List<Transaction> u2Tran = transactionDAO.getAllTransactions(user2,0,0);
		List<Transaction> u3Tran = transactionDAO.getAllTransactions(user3,0,0);
		
		
		
	}


	@Test
	public void testDeleteTransactions(){
		usersDAO.saveUser(user1);
		usersDAO.saveUser(user2);
		
		Account acc1 = new Account(100, user1);
		Account acc2 = new Account(200, user2);
		
		accountDAO.saveOrUpdateAccount(acc1);
		accountDAO.saveOrUpdateAccount(acc2);
		
		accountDAO.transferMoney(acc1, acc2, 543);
			
		transactionDAO.deleteTransactionsInvolving(user1);
		
		List<Transaction> u1Tran = transactionDAO.getAllTransactions(user1,0,0);
		List<Transaction> u2Tran = transactionDAO.getAllTransactions(user2,0,0);
		
		assertEquals(u1Tran.size(), 0);
		assertEquals(u2Tran.size(), 0);
	}

	@Test
	public void testDeleteAll(){
		usersDAO.saveUser(user1);
		usersDAO.saveUser(user2);
		
		Account acc1 = new Account(100, user1);
		Account acc2 = new Account(200, user2);
		
		accountDAO.saveOrUpdateAccount(acc1);
		accountDAO.saveOrUpdateAccount(acc2);
		
		accountDAO.transferMoney(acc1, acc2, 543);
		
		usersService.deleteUser(user1.getUsername());
		
		assertEquals(usersDAO.getAllUsers().size(), 1);
		assertEquals(transactionDAO.getAllTransactions(user2, 0, 0).size(), 0);
	}
	
	

}