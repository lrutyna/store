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

import com.mycompany.store.dto.AddressFormDTO;
import com.mycompany.store.service.AddressService;
import com.mycompany.store.validator.AddressFormValidator;


@Controller
@RequestMapping("/customer")
public class AddressController {
	
	@Autowired
	private AddressService addressService;
	
	@Autowired
	private AddressFormValidator addressFormValidator;

	@GetMapping("/new-address")
	public String accountsList(Model model) {
		model.addAttribute("addressForm", new AddressFormDTO());
		return "newAddress";
	}
	
	@PostMapping("/new-address")
	public String accountSubmit(@ModelAttribute("addressForm") @Valid AddressFormDTO addressForm, BindingResult bindingResult) {
		
		addressFormValidator.validate(addressForm, bindingResult);
		
		if(bindingResult.hasErrors()){ 
			return "newAddress";  
		}
		
		addressService.saveAddress(addressForm);
		return "addressResult";
	}
}
