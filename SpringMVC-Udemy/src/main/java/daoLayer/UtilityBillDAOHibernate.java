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
import domainLayer.UtilityBill;

@Component("utiliyBillDAO")
@Repository 
@Transactional
public class UtilityBillDAOHibernate implements UtilityBillDAO {

	@Autowired
	private SessionFactory sessionFactory;

	public Session session() {
		return sessionFactory.getCurrentSession();
	}
	
	@Override
	public void createUtilityBill(UtilityBill utilityBill) {
		session().saveOrUpdate(utilityBill);
		
	}

	@Override
	public void deleteUtilityBill(UtilityBill utilityBill) {
		session().delete(utilityBill);
		
	}

	@Override
	public List<UtilityBill> getAllUtilityBillsForUser(String username) {
		Criteria crit = session().createCriteria(UtilityBill.class);
		crit.createAlias("user", "u").add(Restrictions.eq("u.username", username));
		return crit.list();
	}

	@Override
	public UtilityBill getUtilityBillById(long idNumber) {
		Criteria crit = session().createCriteria(UtilityBill.class);
		crit.add(Restrictions.idEq(idNumber)); 
		return (UtilityBill) crit.uniqueResult();
	}
	


}
