package com.qa.business.service;

import com.qa.persistence.domain.Account;

public interface AccountService {
	

		String getAllAccounts();

		String addAccount(String account);

		String updateAccount(Long id, String account);

		String deleteAccount(Long id);
		
		Account findAccount (Long id);

	
}
