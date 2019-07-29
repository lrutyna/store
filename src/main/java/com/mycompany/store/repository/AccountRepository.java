package com.mycompany.store.repository;

import java.util.Optional;
import org.springframework.data.repository.PagingAndSortingRepository;
import com.mycompany.store.model.Account;

public interface AccountRepository extends PagingAndSortingRepository<Account,Long>{
	
	Optional<Account> findOneByLogin(String login);
    Optional<Account> findOneByEmail(String email);
}
