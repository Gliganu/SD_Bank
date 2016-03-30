package daoLayer;

import java.util.List;

import domainLayer.Account;
import domainLayer.Transaction;
import domainLayer.User;

public interface TransactionDAO {
	
	void saveTransaction(User fromUser, User toUser, int amount);
	
	List<Transaction> getAllTransactions(User user, long startDate, long endDate);

	void deleteTransactionsInvolving(User user);
	

}
