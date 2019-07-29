package com.mycompany.store.service.impl;

import java.util.HashSet;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.mycompany.store.dto.AccountFormDTO;
import com.mycompany.store.exception.AccountNotFoundException;
import com.mycompany.store.model.Account;
import com.mycompany.store.model.Role;
import com.mycompany.store.repository.AccountRepository;
import com.mycompany.store.repository.RoleRepository;
import com.mycompany.store.service.AccountService;


@Service
public class AccountServiceImpl implements AccountService{

	@Autowired
	private AccountRepository accountRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	
	@Override
	public Optional<Account> getAccountById(Long id) {
		return accountRepository.findById(id);
	}
	
	@Override
	public Optional<Account> getAccountByLogin(String login) {
		return accountRepository.findOneByLogin(login);
	}

	@Override
	public Optional<Account> getAccountByEmail(String email) {
		return accountRepository.findOneByEmail(email);
	}
	
	@Override
	public Iterable<Account> listAllAccounts(){
		return accountRepository.findAll();
	}

	
	@Override
	public Account saveAccount(AccountFormDTO form) {
		Account account = new Account();
		account.setLogin(form.getLogin());
		account.setFirstName(form.getFirstName());
		account.setLastName(form.getLastName());
		account.setEmail(form.getEmail());
		account.setPassword(passwordEncoder.encode(form.getPassword()));
		
		Optional<Role> optionalRole = roleRepository.findOneByName("USER");
		
		if(optionalRole.isPresent()) {
			account.setRoles(new HashSet<>());
			optionalRole.get().setUsers(new HashSet<>());
			optionalRole.get().getUsers().add(account);
			account.getRoles().add(optionalRole.get());
		}
		
		return accountRepository.save(account);
	}
	
	
	@Override
	public Account updateAccount(Account account) {
		return accountRepository.save(account);
	}


	@Override
	public void deleteAccount(Long id) {
		accountRepository.deleteById(id);
	}

	@Override
    public Account getCurrentUser() {
        String loggedInUserName = findLoggedInUsername();
        return accountRepository.findOneByLogin(loggedInUserName).orElseThrow(() -> new AccountNotFoundException(loggedInUserName));
    }

    private String findLoggedInUsername(){
        Object userDetails = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(userDetails instanceof UserDetails){
            return ((UserDetails) userDetails).getUsername();
        }
        return null;
    }
}
