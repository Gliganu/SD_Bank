package webLayer;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.mysql.jdbc.Util;

import daoLayer.AccountDAO;
import domainLayer.Account;
import domainLayer.MoneyTransfer;
import domainLayer.Transaction;
import domainLayer.User;
import serviceLayer.AccountsService;
import serviceLayer.TransactionService;
import serviceLayer.UsersService;

@Controller
public class ActionsController {

	private static String UPDATE_CLIENT_INFO_PAGE = "updateClientInfo";
	private static String UPDATE_ACCOUNTS_INFO_PAGE = "updateAccounts";
	private static String TRANSFER_MONEY_PAGE = "transferMoney";
	private static String VIEW_ALL_CUSTOMERS_PAGE = "viewAllCustomers";
	private static String EDIT_ACCOUNT_PAGE = "editAccount";
	private static String ALL_TRANSACTIONS_PAGE = "allTransactions";
	
	@Autowired
	UsersService usersService;

	@Autowired
	AccountsService accountsService;
	
	@Autowired
	TransactionService transactionService;

	@RequestMapping(value = "/updateClientInfo", method = RequestMethod.GET)
	public String showUpdateClientInfoPage(@RequestParam(value = "username", required = false) String username, Model model, Principal principal) {
			
		if (username == null || username.isEmpty()){
			username = principal.getName();
		}
		
		User loggedInUser = usersService.getUser(username);
		model.addAttribute("finalUser", loggedInUser);

		return UPDATE_CLIENT_INFO_PAGE;
	}

	@RequestMapping(value = "updateClientInfo", method = RequestMethod.POST)
	public String createOffer(@Valid User finalUser, BindingResult result, Model model) {

		if (result.hasErrors()) {
			return UPDATE_CLIENT_INFO_PAGE;
		} else {
			usersService.updateUser(finalUser);
			return Utils.showMessagePage(model, "Account Info Updated");
		}

	}

	@RequestMapping(value = "/updateAccountsInfo", method = RequestMethod.GET)
	public String showUpdateAccountsInfoPage(Model model, Principal principal) {

		List<Account> userAccounts = accountsService.getAllAccountsForUser(principal.getName());

		model.addAttribute("userAccounts", userAccounts);
		return UPDATE_ACCOUNTS_INFO_PAGE;
	}

	
	@RequestMapping(value = "/editAccount", method = RequestMethod.GET)
	public String showEditAccountPage(@RequestParam("id") int idNumber, Model model, Principal principal) {

		Account account = accountsService.getAccountById(idNumber);

		model.addAttribute("account", account);

		return EDIT_ACCOUNT_PAGE;
	}
	
	

	@RequestMapping(value = "/editAccount", method = RequestMethod.POST)
	public String processEditAccount(@Valid Account account, BindingResult result, Model model) {

		if (result.hasErrors()) {
			System.out.println(result.getAllErrors());
			return EDIT_ACCOUNT_PAGE;
		} else {
			
			Account prevAccount = accountsService.getAccountById(account.getIdNumber());
			account.setUser(prevAccount.getUser());
			accountsService.saveOrUpdateAccount(account);

			return Utils.showMessagePage(model, "Account Info Updated");
		}

	}

	@RequestMapping(value = "/deleteAccount", method = RequestMethod.GET)
	public String showDeleteAccountPage(@RequestParam("id") long idNumber, Model model, Principal principal) {

		accountsService.deleteAccount(idNumber);
		
		return Utils.showMessagePage(model, "Account with ID "+ idNumber+ " was deleted successfully");
	}

	@RequestMapping(value = "/deleteCustomerProfile", method = RequestMethod.GET)
	public String showDeleteCustomerPage(@RequestParam("username") String username, Model model, Principal principal) {

		if(username.equals(principal.getName())){
			return Utils.showMessagePage(model, "Cannot delete your own account");
		}
		usersService.deleteUser(username);
		
		return Utils.showMessagePage(model, "Customer "+username+" was deleted successfully");
	}
	
	
	@RequestMapping(value = "/generateTransactionReport", method = RequestMethod.GET)
	public String showAllTransactionsPage(@RequestParam("username") String username, Model model, Principal principal) {

		List<Transaction> allTransactions = transactionService.getAllTransactions(username, 0, 0);
		
		model.addAttribute("allTransactions", allTransactions);
		
		return ALL_TRANSACTIONS_PAGE;
	}
	

	@RequestMapping(value = "/transferMoney", method = RequestMethod.GET)
	public String shotTransferMoneyPage(Model model, Principal principal) {
		
		List<Account> allAccountsForUser = accountsService.getAllAccountsForUser(principal.getName());
		
		List<Long> allAccountsIds = new ArrayList<>();
		
		for(Account account: allAccountsForUser){
			allAccountsIds.add(account.getIdNumber());
		}
		
		model.addAttribute("userAccountsIds", allAccountsIds);
		model.addAttribute("moneyTransfer", new MoneyTransfer());
		
		
		return TRANSFER_MONEY_PAGE;
	}
	
	@RequestMapping(value = "/transferMoney", method = RequestMethod.POST)
	public String processEditAccount(@Valid MoneyTransfer moneyTransfer, BindingResult result,Principal principal, Model model) {

		if (result.hasErrors()) {
			
			List<Account> allAccountsForUser = accountsService.getAllAccountsForUser(principal.getName());
			
			List<Long> allAccountsIds = new ArrayList<>();
			
			for(Account account: allAccountsForUser){
				allAccountsIds.add(account.getIdNumber());
			}	
			
			model.addAttribute("userAccountsIds", allAccountsIds);
			
			return TRANSFER_MONEY_PAGE;
		} else {
		
			accountsService.transferMoney(moneyTransfer);
			
			return Utils.showMessagePage(model, "Money transfered");
		}

	}
	

	@RequestMapping(value = "/viewAllCustomers", method = RequestMethod.GET)
	public String showViewAllCustomesPage(Model model, Principal principal) {
		
		List<User> allUsers = usersService.getAllUsers();
		
		model.addAttribute("allUsers", allUsers);
		
		return VIEW_ALL_CUSTOMERS_PAGE;
	}
	
	@RequestMapping(value = "/createNewAccount", method = RequestMethod.GET)
	public String showCreateNewAccountPage(Model model, Principal principal) {
		
		accountsService.createNewAccount(principal.getName());
		
		List<Account> userAccounts = accountsService.getAllAccountsForUser(principal.getName());

		model.addAttribute("userAccounts", userAccounts);
		
		return UPDATE_ACCOUNTS_INFO_PAGE;
	}

}
