package com.mycompany.store.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class AccountNotFoundException extends RuntimeException{

	 public AccountNotFoundException(String loginOrEmail){
	        super(String.format("Nie znaleziono użytkownika z podaną nazwą lub adresem email: %s", loginOrEmail));
	 }
}
