package com.mycompany.store.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mycompany.store.dto.AddressFormDTO;
import com.mycompany.store.model.Address;
import com.mycompany.store.repository.AddressRepository;
import com.mycompany.store.service.AddressService;


@Service
public class AddressServiceImpl implements AddressService {
	
	@Autowired
	private AddressRepository addressRepository;

	@Override
	public Iterable<Address> listAllAddresses() {
		return addressRepository.findAll();
	}

	@Override
	public Address saveAddress(AddressFormDTO form) {
		Address address = new Address();
		address.setCity(form.getCity());
		address.setCode(form.getCode());
		address.setStreet(form.getStreet());
		
		return addressRepository.save(address);
	}

	@Override
	public Address updateAddress(Address address) {
		return addressRepository.save(address);
	}

	@Override
	public void deleteAddress(Long id) {
		addressRepository.deleteById(id);
	}
	
}
