package com.mycompany.store.service.impl;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.mycompany.store.dto.ProductFormDTO;
import com.mycompany.store.exception.ProductNotFoundException;
import com.mycompany.store.model.Product;
import com.mycompany.store.repository.ProductRepository;
import com.mycompany.store.service.ProductService;


@Service
public class ProductServiceImpl implements ProductService{
	
	@Autowired
	private ProductRepository productRepository;
	

	@Override
	public Optional<Product> getProductByName(String name) {
		return productRepository.findOneByName(name);
	}

	@Override
	public Product getProductById(long id) {
		return productRepository.findOneById(id).orElseThrow(() -> new ProductNotFoundException(id));
	}

	@Override
	public Product saveProduct(ProductFormDTO form) {
		
		Product product = new Product();
		product.setName(form.getName());
		product.setDescription(form.getDescription());
		product.setPrice(form.getPrice());
		product.setQuantityInStock(form.getQuantityInStock());
		product.setCategory(form.getCategory());
		
		return productRepository.save(product);
	}
	
	@Override
	public Product updateProduct(Product product) {
		return productRepository.save(product);
	}

	@Override
	public Page<Product> getAllProducts(Pageable pageable) {
		return productRepository.findAll(pageable);
	}
	
	@Override
	public List<Product> getAllByCategory(String categoryName){
		return productRepository.findAllByCategory(categoryName);
	}
	
	@Override
	public void deleteProduct(Long id) {
		productRepository.deleteById(id);
	}

}
