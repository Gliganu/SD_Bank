package daoLayer;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import domainLayer.User;

public interface UsersDAO {

	void saveUser(User user);
	
	void updateUser(User user);

	boolean exists(String username);

	List<User> getAllUsers();

	User getUser(String username);
	
	void deleteUser(User user);
	
}