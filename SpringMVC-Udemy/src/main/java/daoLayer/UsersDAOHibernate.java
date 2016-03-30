package daoLayer;

import java.util.List;

import javax.sql.DataSource;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import domainLayer.User;

@Component("usersDAO")
@Repository 
@Transactional
public class UsersDAOHibernate implements UsersDAO{
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public Session session(){
		return sessionFactory.getCurrentSession();
	}  
	
	@Override
	@Transactional
	public void saveUser(User user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		session().save(user);
	}
	
	@Override
	@Transactional
	public void updateUser(User user) {
		session().update(user);
	}


	@Override
	public boolean exists(String username) {
		Criteria crit = session().createCriteria(User.class);
		crit.add(Restrictions.idEq(username)); 
		User user=(User) crit.uniqueResult();
		return user != null;
	}

	@Override
	public List<User> getAllUsers() {
		return session().createQuery("from User").list();
	
	}

	@Override
	public User getUser(String username) {
		Criteria crit = session().createCriteria(User.class);
		crit.add(Restrictions.idEq(username)); 
		User user=(User) crit.uniqueResult();
		return user;
	}

	@Override
	public void deleteUser(User user) {
		session().delete(user);
	}

}
