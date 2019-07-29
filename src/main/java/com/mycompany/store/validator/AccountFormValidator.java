package com.mycompany.store.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import com.mycompany.store.dto.AccountFormDTO;
import com.mycompany.store.service.AccountService;


@Component
public class AccountFormValidator implements Validator{
	
	@Autowired
	private AccountService accountService;

	@Override
	public boolean supports(Class<?> arg0) {
		return AccountFormDTO.class.isAssignableFrom(arg0);
	}

	@Override
	public void validate(Object obj, Errors errors) {
		AccountFormDTO form = (AccountFormDTO)obj;
		
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "notEmpty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstName", "notEmpty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastName", "notEmpty");
        
        String login = form.getLogin();
        int loginLength = login.length();

        if(loginLength < 5 || loginLength > 30){
            errors.rejectValue("login", "Size.accountForm.login");
        }
        if(accountService.getAccountByLogin(login).isPresent()){
            errors.rejectValue("login", "Duplicate.accountForm.login");
        }
        if(accountService.getAccountByEmail(form.getEmail()).isPresent()){
            errors.rejectValue("email", "Duplicate.accountForm.email");
        }
	}

}
