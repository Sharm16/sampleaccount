package com.qa.persistence;

import static javax.transaction.Transactional.TxType.REQUIRED;
import static javax.transaction.Transactional.TxType.SUPPORTS;

import java.util.Collection;

import javax.enterprise.inject.Default;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.apache.log4j.Logger;
import com.qa.persistence.domain.Account;
import com.qa.util.JSONUtil;

@Transactional(SUPPORTS)
@Default
public class AccountDBRepository implements AccountRepository {

	@PersistenceContext(unitName = "primary")
	private EntityManager manager;
	private static final Logger LOGGER = Logger.getLogger(AccountRepository.class);

	@Inject
	private JSONUtil util;

	public String getAllAccounts() {
		LOGGER.info("In persistence accountDBrepository getAllAccounts()");
		Query query = manager.createQuery("Select a FROM Account a");
		Collection<Account> accounts = (Collection<Account>) query.getResultList();
		return util.getJSONForObject(accounts);
	}

	@Transactional(REQUIRED)
	public String createAccount(String accout) {
		LOGGER.info("In persistence accountDBrepository createAccounts()");
		Account anAccount = util.getObjectForJSON(accout, Account.class);
		manager.persist(anAccount);
		return "{\"message\": \"account has been sucessfully added\"}";
	}

	@Transactional(REQUIRED)
	public String updateAccount(Long id, String accountToUpdate) {
		LOGGER.info("In persistence accountDBrepository updateAccounts()");
		Account updatedAccount = util.getObjectForJSON(accountToUpdate, Account.class);
		Account accountFromDB = findAccount(id);
		Query query = manager.createQuery("Select a  FROM Account a where id = "+id);
		if (query.getResultList().isEmpty()) {
			LOGGER.error("ID NOT VALID");	
			return "{\"message\": \"account not found\"}";
		}
		else 
			if (accountToUpdate != null) {
				accountFromDB = updatedAccount;
				accountFromDB.setId(id);
				manager.merge(accountFromDB);
			}
			return "{\"message\": \"account sucessfully updated\"}";
	}

	@Transactional(REQUIRED)
	public String deleteAccount(Long id) {
		LOGGER.info("In persistence accountDBrepository deleteAccounts()");
		Account accountInDB = findAccount(id);
		if (accountInDB != null) {
			manager.remove(accountInDB);
		}
		return "{\"message\": \"account sucessfully deleted\"}";
	}

	public Account findAccount(Long id) {
		LOGGER.info("In persistence accountDBrepository findAccounts()");
		return manager.find(Account.class, id);
	}

	public void setManager(EntityManager manager) {
		this.manager = manager;
	}

	public void setUtil(JSONUtil util) {
		this.util = util;
	}

}
