package com.mycompany.store.service;

import java.util.Optional;

import com.mycompany.store.dto.AccountFormDTO;
import com.mycompany.store.model.Account;

public interface AccountService {

	Optional<Account> getAccountById(Long id);
	
	Optional<Account> getAccountByLogin(String login);
	
    Optional<Account> getAccountByEmail(String email);
    
	Iterable<Account> listAllAccounts();
	
    Account saveAccount(AccountFormDTO form);
    
    Account updateAccount(Account account);
    
    void deleteAccount(Long id);
    
    Account getCurrentUser();
}
