package com.mycompany.store.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.mycompany.store.dto.AddressFormDTO;


@Component
public class AddressFormValidator implements Validator{

	@Override
	public boolean supports(Class<?> arg0) {
		return AddressFormDTO.class.isAssignableFrom(arg0);
	}

	@Override
	public void validate(Object obj, Errors errors) {
		AddressFormDTO form = (AddressFormDTO)obj;
		
	}

}
