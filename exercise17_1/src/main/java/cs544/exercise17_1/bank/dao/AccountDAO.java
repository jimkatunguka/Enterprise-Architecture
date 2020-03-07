package cs544.exercise17_1.bank.dao;

import java.util.*;

import cs544.exercise17_1.bank.domain.Account;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;

public class AccountDAO implements IAccountDAO {
	private SessionFactory sf;

	public void setSessionFactory(SessionFactory sf){
		this.sf = sf;
	}

	public void saveAccount(Account account) {
		sf.getCurrentSession().persist(account);
	}

	public void updateAccount(Account account) {
		sf.getCurrentSession().saveOrUpdate(account);
	}

	public Account loadAccount(long accountnumber) {
		return (Account) sf.getCurrentSession().get(Account.class, accountnumber);
	}

	public Collection<Account> getAccounts() {
		return sf.getCurrentSession().createQuery("from Account").list();
	}
}
