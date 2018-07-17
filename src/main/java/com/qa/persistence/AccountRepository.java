package com.qa.persistence;

import com.qa.persistence.domain.Account;

public interface AccountRepository {

	String getAllAccounts();

	String createAccount(String accout);

	String updateAccount(Long id, String accountToUpdate);

	String deleteAccount(Long id);
	
	Account findAccount(Long id);

}
