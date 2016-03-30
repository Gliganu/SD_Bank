package serviceLayer;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import daoLayer.TransactionDAO;
import domainLayer.Transaction;
import domainLayer.User;

@Service("transactionsService")

public class TransactionService {
	
	@Autowired
	TransactionDAO transactionDAO;
	
	@Autowired
	private UsersService usersService;
	
	public void saveTransaction(User fromUser, User toUser, int amount){
		transactionDAO.saveTransaction(fromUser, toUser, amount);
	}
	
	public List<Transaction> getAllTransactions(String username, long startDate, long endDate){
		
		User user = usersService.getUser(username);
		
		return transactionDAO.getAllTransactions(user, startDate, endDate);
	}
	
	public void deleteTransactionsInvolving(User user){
	
		transactionDAO.deleteTransactionsInvolving(user);
	}
}
