package com.mycompany.store.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.mycompany.store.dto.ProductFormDTO;
import com.mycompany.store.model.Product;

public interface ProductService {
	
	 Optional<Product> getProductByName(String name);

	 Product getProductById(long id);

	 Product saveProduct(ProductFormDTO form);
	 
	 Product updateProduct(Product product);

	 Page<Product> getAllProducts(Pageable pageable);
	 
	 List<Product> getAllByCategory(String categoryName);
	 
	 void deleteProduct(Long id);
}
