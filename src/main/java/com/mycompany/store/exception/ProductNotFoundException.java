package com.mycompany.store.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ProductNotFoundException extends RuntimeException{

	public ProductNotFoundException(long id) {
		super(String.format("Nie znaleziono produktu o podanym ID: %d", id));
	}
}
