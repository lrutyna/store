package com.mycompany.store.service;

import com.mycompany.store.dto.AddressFormDTO;
import com.mycompany.store.model.Address;

public interface AddressService {

	Iterable<Address> listAllAddresses();
	
    Address saveAddress(AddressFormDTO form);
    
    Address updateAddress(Address address);
    
    void deleteAddress(Long id);
}
