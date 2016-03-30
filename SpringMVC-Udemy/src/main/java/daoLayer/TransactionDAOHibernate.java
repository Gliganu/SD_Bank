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
import domainLayer.Transaction;
import domainLayer.User;

@Component("transactionDAO")
@Repository 
@Transactional
public class TransactionDAOHibernate implements TransactionDAO {

	@Autowired
	private SessionFactory sessionFactory;

	public Session session() {
		return sessionFactory.getCurrentSession();
	}
	
	@Override
	public List<Transaction> getAllTransactions(User user, long startDate, long endDate) {
		
		Criteria crit=session().createCriteria(Transaction.class);
		crit.createAlias("fromUser", "from");
		crit.createAlias("toUser", "to");

		crit.add(Restrictions.disjunction()
	        .add(Restrictions.eq("from.username",user.getUsername()))
	        .add(Restrictions.eq("to.username",user.getUsername()))
	    );
		
		if(startDate != 0 && endDate != 0){
			crit.add(Restrictions.between("date", startDate, endDate));			
		}
		

		return  crit.list();
	}

	@Override
	public void saveTransaction(User fromUser, User toUser, int amount) {
		Transaction transaction = new Transaction(fromUser, toUser, amount);
		
		session().saveOrUpdate(transaction);
		
	}

	@Override
	public void deleteTransactionsInvolving(User user) {
		List<Transaction> allTransactions = getAllTransactions(user,0,0);
		
		System.out.println(allTransactions);
		for(Transaction transaction: allTransactions){
			session().delete(transaction);
		}

		
	}



	

}
