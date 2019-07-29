package com.mycompany.store.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.mycompany.store.dto.AccountFormDTO;
import com.mycompany.store.service.AccountService;
import com.mycompany.store.validator.AccountFormValidator;
import com.mycompany.store.validator.RegistrationFormValidator;


@Controller
@RequestMapping("/registration")
public class RegistrationController {

	@Autowired
	private AccountService accountService;
	
	@Autowired
	private RegistrationFormValidator registrationFormValidator;
	
	@GetMapping
	public String accountForm(Model model) {
		model.addAttribute("accountForm", new AccountFormDTO());
		return "newAccount";
	}
	
	@PostMapping
	public String accountSubmit(@ModelAttribute("accountForm") @Valid AccountFormDTO accountForm, BindingResult bindingResult) {
		
		registrationFormValidator.validate(accountForm, bindingResult);
		
		if(bindingResult.hasErrors()){ 
			return "newAccount";  
		}
		
		accountService.saveAccount(accountForm);
		return "registrationResult";
	}
	
}
