package com.mycompany.store.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.mycompany.store.model.Category;

public interface CategoryRepository extends PagingAndSortingRepository<Category,Long> {
 
}
