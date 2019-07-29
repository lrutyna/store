package com.mycompany.store.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.mycompany.store.model.Product;


public interface ProductRepository extends PagingAndSortingRepository<Product,Long>{

	Optional<Product> findOneByName(String name);
    
	Optional<Product> findOneById(long id);
    
	Page<Product> findAll(Pageable pageable);
    
    @Query("select p from Product p join p.category c where c.name = :categoryName")
    List<Product> findAllByCategory(@Param(value = "categoryName") String categoryName);
}
