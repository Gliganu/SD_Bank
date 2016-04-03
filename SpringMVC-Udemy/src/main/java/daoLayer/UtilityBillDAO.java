package daoLayer;

import java.util.List;

import domainLayer.User;
import domainLayer.UtilityBill;

public interface UtilityBillDAO {

	void createUtilityBill(UtilityBill utilityBill);
	
	void deleteUtilityBill(UtilityBill utilityBill);
	
	List<UtilityBill> getAllUtilityBillsForUser(String username);
	
	UtilityBill getUtilityBillById(long idNumber);
}
