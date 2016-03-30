package daoLayer;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import domainLayer.Account;
import domainLayer.User;

@Component("accountDAO")
@Repository 
@Transactional
public class AccountDAOHibernate implements AccountDAO {

	@Autowired
	private SessionFactory sessionFactory;


	@Autowired
	private TransactionDAO transactionDAO;
	
	
	public Session session() {
		return sessionFactory.getCurrentSession();
	}

	@Override
	public void saveOrUpdateAccount(Account account) {
		session().saveOrUpdate(account);
	}

	@Override
	public List<Account> getAllAccountsForUser(User user) {
		Criteria crit = session().createCriteria(Account.class);
		crit.createAlias("user", "u").add(Restrictions.eq("u.username", user.getUsername()));
		return crit.list();

	}

	@Override
	public List<Account> getAllAccounts() {
		return session().createQuery("from Account").list();
	}

	@Override
	public void transferMoney(Account fromAccount, Account toAccount, int tranferAmount) {

		saveOrUpdateAccount(fromAccount);
		saveOrUpdateAccount(toAccount);
		
		transactionDAO.saveTransaction(fromAccount.getUser(),toAccount.getUser(),tranferAmount);

		
	}
	
	@Override
	public void deleteAccount(Account account) {
		session().delete(account);
		
	}

	@Override
	public Account getAccountById(long idNumber) {
		Criteria crit = session().createCriteria(Account.class);
		crit.add(Restrictions.idEq(idNumber)); 
		return (Account) crit.uniqueResult();
	}

}
