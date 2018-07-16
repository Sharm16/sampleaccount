package com.qa.persistence;

public interface AccountRepository {

	String getAllAccounts();

	String createAccount(String accout);

	String updateAccount(Long id, String accountToUpdate);

	String deleteAccount(Long id);

}
