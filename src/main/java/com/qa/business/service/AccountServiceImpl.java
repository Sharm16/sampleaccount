package com.qa.business.service;

import javax.inject.Inject;

import org.apache.log4j.Logger;

import com.qa.persistence.AccountRepository;
import com.qa.persistence.domain.Account;

public class AccountServiceImpl implements AccountService {

	private static final Logger LOGGER = Logger.getLogger(AccountService.class);

	@Inject
	private AccountRepository repo;

	public String getAllAccounts() {
		LOGGER.info("In business.service AccountServiceImpl getAllAccounts ");
		return repo.getAllAccounts();
	}

	@Override
	public String addAccount(String account) {
		LOGGER.info("In business.service AccountServiceImpl addAccount ");
		return repo.createAccount(account);
	}

	@Override
	public String updateAccount(Long id, String account) {
		LOGGER.info("In business.service AccountServiceImpl updateAccount ");
		return repo.updateAccount(id, account);
	}

	@Override
	public String deleteAccount(Long id) {
		LOGGER.info("In business.service AccountServiceImpl deleteAccount ");
		return repo.deleteAccount(id);

	}
	@Override
	public Account findAccount(Long id) {
		LOGGER.info("In business.service AccountServiceImpl deleteAccount ");
		return repo.findAccount(id);

	}

	public void setRepo(AccountRepository repo) {
		this.repo = repo;
	}
}