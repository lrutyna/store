package com.mycompany.store.controllers;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mycompany.store.dto.AccountFormDTO;
import com.mycompany.store.model.Account;
import com.mycompany.store.service.AccountService;
import com.mycompany.store.validator.AccountFormValidator;


@Controller
@RequestMapping("/admin-panel")
public class AccountController {
	
	@Autowired
	private AccountService accountService;
	
	@Autowired
	private AccountFormValidator accountFormValidator;

	
	@GetMapping("/accounts")
	public String accountsList(Model model) {
		model.addAttribute("accounts", accountService.listAllAccounts());
		return "accountsPanel";
	}
	
	@GetMapping("/edit-account/{id}")
	public String accountForm(@PathVariable String id, Model model) {
		
		Optional<Account> optionalAccount = accountService.getAccountById(Long.parseLong(id));
		
		optionalAccount.ifPresent(account -> {
			AccountFormDTO formDTO = new AccountFormDTO();
			formDTO.setId(account.getId());
			formDTO.setFirstName(account.getFirstName());
			formDTO.setLastName(account.getLastName());
			formDTO.setLogin(account.getLogin());
			formDTO.setEmail(account.getEmail());
			
			model.addAttribute("accountForm", formDTO);
		});
		
		return "editAccount";
	} 
	
	
	@PostMapping("/edit-account")
	public String editAccount(@ModelAttribute("accountForm") @Valid AccountFormDTO accountForm, BindingResult bindingResult) {
		
		accountFormValidator.validate(accountForm, bindingResult);
		
		if(bindingResult.hasErrors()){ 
			return "editAccount";  
		}
		
		Optional<Account> optionalAccount = accountService.getAccountById(accountForm.getId());
		
		optionalAccount.ifPresent(account -> {
			account.setId(accountForm.getId());
			account.setFirstName(accountForm.getFirstName());
			account.setLastName(accountForm.getLastName());
			account.setLogin(accountForm.getLogin());
			account.setEmail(accountForm.getEmail());
			
			accountService.updateAccount(account);
		});
		
		return "redirect:/admin-panel/accounts";
	}
	
	
	@GetMapping("/remove-account/{id}")
	public String deleteAccount(@PathVariable String id , Model model) {
		accountService.deleteAccount(Long.parseLong(id));
		
		return "redirect:/admin-panel/accounts";
	}
	
	
}
