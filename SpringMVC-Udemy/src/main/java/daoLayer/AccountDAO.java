package daoLayer;

import java.util.List;

import domainLayer.Account;
import domainLayer.User;

public interface AccountDAO {
	
	void saveOrUpdateAccount(Account account);

	List<Account> getAllAccountsForUser(User user);

	List<Account> getAllAccounts();

	boolean transferMoney(Account fromAccount, Account toAccount, int tranferAmount);
	
	void deleteAccount(Account account);
	
	Account getAccountById(long idNumber);
}
