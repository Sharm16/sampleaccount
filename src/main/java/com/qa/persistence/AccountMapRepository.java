package com.qa.persistence;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Alternative;
import javax.inject.Inject;

import org.apache.log4j.Logger;

import com.qa.persistence.domain.Account;
import com.qa.persistence.domain.Transaction;
import com.qa.util.JSONUtil;

@ApplicationScoped
@Alternative
public class AccountMapRepository implements AccountRepository {

	private final Long INITIAL_COUNT = 1L;
	private Map<Long, Account> accountMap;
	private Long ID;
	private static final Logger LOGGER = Logger.getLogger(AccountRepository.class);

	@Inject
	private JSONUtil util;

	public AccountMapRepository() {
		LOGGER.info("In persistence accountMapRepository accountMapRepository()");
		this.accountMap = new HashMap<Long, Account>();
		ID = INITIAL_COUNT;
		initAccountMap();
	}
	
	public Account findAccount(Long id) {
		LOGGER.info("In persistence accountMapRepository findAccounts()");
		return accountMap.get(id);
	}

	
	public String getAllAccounts() {
		LOGGER.info("In persistence accountDBrepository getAllAccounts()");
		return util.getJSONForObject(accountMap.values());
	}


	public String createAccount(String account) {
		LOGGER.info("In persistence accountMapRepository createAccount()");
		ID++;
		Account newAccount = util.getObjectForJSON(account, Account.class);
		accountMap.put(ID, newAccount);
		return account;
	}

	
	public String updateAccount(Long id, String accountToUpdate) {
		LOGGER.info("In persistence accountMapRepository updateAccount()");
		Account newAccount = util.getObjectForJSON(accountToUpdate, Account.class);
		accountMap.put(id, newAccount);
		return accountToUpdate;
	}

	
	public String deleteAccount(Long id) {
		LOGGER.info("In persistence accountMapRepository deleteAccounts()");
		accountMap.remove(id);
		return "{\"message\": \"accout sucessfully removed\"}";
	}

	private void initAccountMap() {
		LOGGER.info("In persistence accountMapRepository initAccountMap()");
		Transaction transaction = new Transaction("sample");
		transaction.setId(1L);
		List<Transaction> transactions = new ArrayList<>();
		transactions.add(transaction);
		Account account = new Account("Joe", "Bloggs", "1234",transactions);
		accountMap.put(1L, account);
	}

}