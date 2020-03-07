package cs544.exercise17_1.bank.dao;

import java.util.*;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;



import cs544.exercise17_1.bank.domain.Account;

@Transactional(propagation = Propagation.MANDATORY)
public class AccountDAO implements IAccountDAO {

	Collection<Account> accountlist = new ArrayList<Account>();


	private SessionFactory sessionFactory;

	public AccountDAO() {

	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public void saveAccount(Account account) {
		sessionFactory.getCurrentSession().persist(account);
	}

	public void updateAccount(Account account) {

		sessionFactory.getCurrentSession().saveOrUpdate(account);
	}

	public Account loadAccount(long accountnumber) {

		return (Account) sessionFactory.getCurrentSession().get(Account.class, accountnumber);
	}

	@SuppressWarnings("unchecked")
	public List<Account> getAccounts() {
		return sessionFactory.getCurrentSession().createQuery("from Account").list();
	}

}
