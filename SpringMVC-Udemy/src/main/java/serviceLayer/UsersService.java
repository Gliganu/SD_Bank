package serviceLayer;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;

import daoLayer.UsersDAO;
import domainLayer.User;

@Service("usersService")
public class UsersService {

	@Autowired
	private UsersDAO usersDao;
	
	@Autowired
	private TransactionService transactionService;
	
	@Autowired
	private AccountsService accountsService;
	
	public void saveUser(User user){
		usersDao.saveUser(user);
	}
	
	public void updateUser(User user){
		usersDao.updateUser(user);
	}

	public void deleteUser(String username){
		User user = getUser(username);
		
		transactionService.deleteTransactionsInvolving(user);
		accountsService.deleteAccountsBelongingTo(user);
		usersDao.deleteUser(user);
	}

	public boolean exists(String username) {
		return usersDao.exists(username);
	}


	public List<User> getAllUsers() {
		return usersDao.getAllUsers();
	}
	
	
	public User getUser(String username){
		return usersDao.getUser(username);
		
	}


}
