package com.mycompany.store.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.mycompany.store.model.Address;

public interface AddressRepository extends PagingAndSortingRepository<Address,Long>{

}
