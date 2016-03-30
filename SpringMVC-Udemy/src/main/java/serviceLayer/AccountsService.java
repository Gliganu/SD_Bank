package serviceLayer;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import daoLayer.AccountDAO;
import domainLayer.Account;
import domainLayer.MoneyTransfer;
import domainLayer.User;
import webLayer.Utils;

@Service("accountsService")
public class AccountsService {

	@Autowired
	private AccountDAO accountDAO;
	
	@Autowired
	private UsersService usersService;

	public void saveOrUpdateAccount(Account account) {
		accountDAO.saveOrUpdateAccount(account);
	}

	public List<Account> getAllAccountsForUser(String username) {
		
		User user = usersService.getUser(username);
		return accountDAO.getAllAccountsForUser(user);
	}

	public List<Account> getAllAccounts() {
		return accountDAO.getAllAccounts();
	}

	public boolean transferMoney(MoneyTransfer moneyTransfer) {
		
		Account fromAccount = getAccountById(moneyTransfer.getFromAccountId());
		Account toAccount = getAccountById(moneyTransfer.getToAccountId());
		int tranferAmount = moneyTransfer.getSum();
		
		if(toAccount == null || fromAccount == null){
			return false;
		}
		
		if (fromAccount.getMoneyAmount() < tranferAmount) {
			return false;
		}

		toAccount.setMoneyAmount(toAccount.getMoneyAmount() + tranferAmount);
		fromAccount.setMoneyAmount(fromAccount.getMoneyAmount() - tranferAmount);
		
	
		accountDAO.transferMoney(fromAccount, toAccount, tranferAmount);
			
		return true;
	}

	public void deleteAccount(long idNumber) {
		Account account = getAccountById(idNumber);
		accountDAO.deleteAccount(account);
	}
	
	public Account getAccountById(long idNumber){
		return accountDAO.getAccountById(idNumber);
	
	}

	public void createNewAccount(String username) {
		
		User user = usersService.getUser(username);
		Account account  = new Account(0, user);
		
		saveOrUpdateAccount(account);
		
	}

	public void deleteAccountsBelongingTo(User user) {
		List<Account> allAccountsForUser = getAllAccountsForUser(user.getUsername());
		
		for(Account account: allAccountsForUser){
			accountDAO.deleteAccount(account);
		}
		
	}

}
