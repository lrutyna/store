package com.mycompany.store.controllers;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mycompany.store.dto.ProductFormDTO;
import com.mycompany.store.model.Category;
import com.mycompany.store.model.Order;
import com.mycompany.store.model.Product;
import com.mycompany.store.repository.CategoryRepository;
import com.mycompany.store.repository.OrderRepository;
import com.mycompany.store.service.AccountService;
import com.mycompany.store.service.ProductService;
import com.mycompany.store.service.SecurityService;


@Controller
public class ProductController {
	
	@Autowired
	private ProductService productService;
	
	@Autowired 
	private CategoryRepository categoryRepository;
	
	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private AccountService accountService;
	
	
	@GetMapping("/admin-panel/products-by-category")
	public String categoriesList(Model model) {
		model.addAttribute("categories", categoryRepository.findAll());
		
		return "product/productsPanel";
	}
	
	
	@GetMapping("/admin-panel/products-by-category/{name}")
	@ResponseBody
	public List<Product> productsList(@PathVariable String name) {
		
		return productService.getAllByCategory(name);
	}
	

	@GetMapping("/product/{id}")
	public String getProductDetails(@PathVariable String id, Model model) {
		model.addAttribute("product", productService.getProductById(Long.parseLong(id)));
		
		return "product/productDetails";
	}
	
	
	@PostMapping("/product/{id}")
	public String addProductToCart(@PathVariable String id, Model model) {
		model.addAttribute("product", productService.getProductById(Long.parseLong(id)));
		
		
		
		return "product/productDetails";
	}

	
	@GetMapping("/admin-panel/new-product")
	public String addNewProduct(Model model) {
		model.addAttribute("productForm", new ProductFormDTO());
		model.addAttribute("categories", categoryRepository.findAll());
		
		return "product/newProduct";
	}

	
	@PostMapping("/admin-panel/new-product")
	public String addNewProduct(@ModelAttribute("productForm") @Valid ProductFormDTO form, BindingResult bindingResult, Model model)
	{
		if (bindingResult.hasErrors()) {
			model.addAttribute("categories", categoryRepository.findAll());
			return "product/newProduct";
		}
		
		Optional<Category> optionalCategory = categoryRepository.findById(form.getCategoryId());
		
		if(optionalCategory.isPresent()) {
			form.setCategory(optionalCategory.get());
			productService.saveProduct(form);
		}
		
		model.addAttribute("product", form);
	
		return "product/productResult";
	}
	
	
	@GetMapping("/admin-panel/edit-product/{id}")
	public String productForm(@PathVariable String id, Model model) {
		
		Product product = productService.getProductById(Long.parseLong(id));
		
		ProductFormDTO form = new ProductFormDTO();
		form.setId(product.getId());
		form.setName(product.getName());
		form.setDescription(product.getDescription());
		form.setPrice(product.getPrice());
		form.setQuantityInStock(product.getQuantityInStock());
		form.setCategoryId(product.getCategory().getId());
		
		model.addAttribute("productForm", form);
		model.addAttribute("categories", categoryRepository.findAll());
		
		return "product/editProduct";
	} 
	
	
	@PostMapping("/admin-panel/edit-product")
	public String editProduct(@ModelAttribute("productForm") @Valid ProductFormDTO form, BindingResult bindingResult, Model model) {
		
		if (bindingResult.hasErrors()) {
			model.addAttribute("categories", categoryRepository.findAll());
			return "product/editProduct";
		}
		
		Optional<Category> optionalCategory = categoryRepository.findById(form.getCategoryId());
		Product product = productService.getProductById(form.getId());
		
		if(optionalCategory.isPresent()) {
			product.setCategory(optionalCategory.get());
			product.setId(form.getId());
			product.setName(form.getName());
			product.setPrice(form.getPrice());
			product.setDescription(form.getDescription());
			product.setQuantityInStock(form.getQuantityInStock());
			
			productService.updateProduct(product);
		}
		
		model.addAttribute("product", product);
	
		return "product/productResult";
	}
	
	
	@GetMapping("/admin-panel/remove-product/{id}")
	public String deleteProduct(@PathVariable String id, Model model) {
		productService.deleteProduct(Long.parseLong(id));
		return "redirect:/admin-panel/products-by-category";
	}
}
