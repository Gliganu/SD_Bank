package serviceLayer;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import daoLayer.UtilityBillDAO;
import domainLayer.Account;
import domainLayer.User;
import domainLayer.UtilityBill;

@Service("utilityBillsService")
public class UtilityBillService {

	@Autowired
	UtilityBillDAO utilityBillDAO;
	
	@Autowired
	AccountsService accountsService;
	
	public void createUtilityBill(UtilityBill utilityBill){
		utilityBillDAO.createUtilityBill(utilityBill);
	}
	
	public void deleteUtilityBill(UtilityBill utilityBill){
		utilityBillDAO.deleteUtilityBill(utilityBill);
	}
	
	public List<UtilityBill> getAllUtilityBillsForUser(String username){
		return utilityBillDAO.getAllUtilityBillsForUser(username);
	}
	
	public void deleteBillsBeloningTo(String username) {
		List<UtilityBill> allUtilitiesForUser = getAllUtilityBillsForUser(username);
		
		for(UtilityBill utilityBill: allUtilitiesForUser){
			utilityBillDAO.deleteUtilityBill(utilityBill);
		}
		
	}

	public String payBill(long idNumber, String username) {
		
		UtilityBill utilityBill = utilityBillDAO.getUtilityBillById(idNumber);
		
		int ammountToPay = utilityBill.getAmmountToPay();
		
		List<Account> userAccounts = accountsService.getAllAccountsForUser(username);
		
		for(Account account : userAccounts){
			if(account.getMoneyAmount() > ammountToPay){
				account.setMoneyAmount(account.getMoneyAmount() - ammountToPay);
				accountsService.saveOrUpdateAccount(account);
				utilityBillDAO.deleteUtilityBill(utilityBill);
				return "Transaction successful";
			}
		}
		

		
		return "You don't have enough money in any of your accounts";
		
	}

}
