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
import daoLayer.UtilityBillDAO;
import domainLayer.Account;
import domainLayer.User;
import domainLayer.UtilityBill;

@ActiveProfiles("dev")
@ContextConfiguration(locations = { "file:src/main/java/config/security-context.xml",
		"file:src/test/java/testConfig/datasource.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
public class UtilityDAOTests {

	@Autowired
	private UsersDAO usersDAO;

	@Autowired
	private UtilityBillDAO utilityBillDAO;
	

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

		UtilityBill u1 = new UtilityBill(user1, 10);
		UtilityBill u2 = new UtilityBill(user1, 20);
		UtilityBill u3 = new UtilityBill(user2, 40);
		UtilityBill u4 = new UtilityBill(user3, 50);


		utilityBillDAO.createUtilityBill(u1);
		utilityBillDAO.createUtilityBill(u2);
		utilityBillDAO.createUtilityBill(u3);
		utilityBillDAO.createUtilityBill(u4);
		
		List<UtilityBill> user1Bills = utilityBillDAO.getAllUtilityBillsForUser(user1.getUsername());
		List<UtilityBill> user2Bills = utilityBillDAO.getAllUtilityBillsForUser(user2.getUsername());
		List<UtilityBill> user3Bills = utilityBillDAO.getAllUtilityBillsForUser(user3.getUsername());

		assertEquals("Should be 2", 2, user1Bills.size());
		assertEquals("Should be 1", 1, user2Bills.size());
		assertEquals("Should be 1", 1, user3Bills.size());

	}

	@Test
	public void testDeleteAccounts(){
		usersDAO.saveUser(user1);
		
		UtilityBill u1 = new UtilityBill(user1, 10);
		UtilityBill u2 = new UtilityBill(user1, 20);

		utilityBillDAO.createUtilityBill(u1);
		utilityBillDAO.createUtilityBill(u2);

		utilityBillDAO.deleteUtilityBill(u1);
		utilityBillDAO.deleteUtilityBill(u2);
		
		List<UtilityBill> user1Bills = utilityBillDAO.getAllUtilityBillsForUser(user1.getUsername());

		assertEquals("Should be 0", 0, user1Bills.size());
		
	}
	

}