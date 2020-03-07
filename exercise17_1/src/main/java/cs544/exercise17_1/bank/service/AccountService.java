package cs544.exercise17_1.bank.service;

import java.util.Collection;

import cs544.exercise17_1.bank.dao.AccountDAO;
import cs544.exercise17_1.bank.dao.IAccountDAO;
import cs544.exercise17_1.bank.domain.Account;
import cs544.exercise17_1.bank.domain.Customer;
import cs544.exercise17_1.bank.jms.IJMSSender;
import cs544.exercise17_1.bank.jms.JMSSender;
import cs544.exercise17_1.bank.logging.ILogger;
import cs544.exercise17_1.bank.logging.Logger;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;

public class AccountService implements IAccountService {
	private IAccountDAO accountDAO;
	private ICurrencyConverter currencyConverter;
	private IJMSSender jmsSender;
	private ILogger logger;

	@Autowired
	private SessionFactory sf;

	private void setSessionFactory(SessionFactory sf){
		this.sf = sf;
	}

	public void setAccountDao(AccountDAO accountDao){
		this.accountDAO = accountDao;
	}

	public void setCurrencyConverter(CurrencyConverter currencyConverter){
		this.currencyConverter = currencyConverter;
	}

	public void setJmsSender(JMSSender jmsSender){
		this.jmsSender = jmsSender;
	}

	public void setLogger(Logger logger){
		this.logger = logger;
	}


	public Account createAccount(long accountNumber, String customerName) {

		try {
			Transaction tx = sf.getCurrentSession().beginTransaction();
			Account account = new Account(accountNumber);
			Customer customer = new Customer(customerName);
			account.setCustomer(customer);
			accountDAO.saveAccount(account);
			tx.commit();

			logger.log("createAccount with parameters accountNumber= " + accountNumber + " , customerName= " + customerName);
			return account;
		}catch (Exception e){
			e.printStackTrace();
		}
		return null;
	}


	public void deposit(long accountNumber, double amount) {

		Transaction tx = sf.getCurrentSession().beginTransaction();
		Account account = accountDAO.loadAccount(accountNumber);
		account.deposit(amount);

		accountDAO.updateAccount(account);

		logger.log("deposit with parameters accountNumber= "+accountNumber+" , amount= "+amount);
		if (amount > 10000){
			jmsSender.sendJMSMessage("Deposit of $ "+amount+" to account with accountNumber= "+accountNumber);
		}
		tx.commit();
	}

	public Account getAccount(long accountNumber) {
		Transaction tx = sf.getCurrentSession().beginTransaction();
		Account account = accountDAO.loadAccount(accountNumber);
		tx.commit();
		return account;
	}

	public Collection<Account> getAllAccounts() {
		//return accountDAO.getAccounts();

		Transaction tx = sf.getCurrentSession().beginTransaction();
		Collection<Account> cl = accountDAO.getAccounts();
		tx.commit();
		return cl;
	}

	public void withdraw(long accountNumber, double amount) {
		Transaction tx = sf.getCurrentSession().beginTransaction();
		Account account = accountDAO.loadAccount(accountNumber);
		account.withdraw(amount);
		accountDAO.updateAccount(account);
		logger.log("withdraw with parameters accountNumber= "+accountNumber+" , amount= "+amount);
		tx.commit();
	}


	public void depositEuros(long accountNumber, double amount) {
		Transaction tx = sf.getCurrentSession().beginTransaction();
		Account account = accountDAO.loadAccount(accountNumber);
		double amountDollars = currencyConverter.euroToDollars(amount);
		account.deposit(amountDollars);
		accountDAO.updateAccount(account);
		logger.log("depositEuros with parameters accountNumber= "+accountNumber+" , amount= "+amount);
		if (amountDollars > 10000){
			jmsSender.sendJMSMessage("Deposit of $ "+amount+" to account with accountNumber= "+accountNumber);
		}
		tx.commit();
	}

	public void withdrawEuros(long accountNumber, double amount) {
		Transaction tx = sf.getCurrentSession().beginTransaction();
		Account account = accountDAO.loadAccount(accountNumber);
		double amountDollars = currencyConverter.euroToDollars(amount);
		account.withdraw(amountDollars);
		accountDAO.updateAccount(account);
		logger.log("withdrawEuros with parameters accountNumber= "+accountNumber+" , amount= "+amount);
		tx.commit();
	}


	public void transferFunds(long fromAccountNumber, long toAccountNumber, double amount, String description) {
		Transaction tx = sf.getCurrentSession().beginTransaction();
		Account fromAccount = accountDAO.loadAccount(fromAccountNumber);
		Account toAccount = accountDAO.loadAccount(toAccountNumber);
		fromAccount.transferFunds(toAccount, amount, description);
		accountDAO.updateAccount(fromAccount);
		accountDAO.updateAccount(toAccount);
		logger.log("transferFunds with parameters fromAccountNumber= "+fromAccountNumber+" , toAccountNumber= "+toAccountNumber+" , amount= "+amount+" , description= "+description);
		if (amount > 10000){
			jmsSender.sendJMSMessage("TransferFunds of $ "+amount+" from account with accountNumber= "+fromAccount+" to account with accountNumber= "+toAccount);
		}
		tx.commit();
	}
}
