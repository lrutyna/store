package com.mycompany.store.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mycompany.store.service.ProductService;

@Controller
@RequestMapping("/")
public class HomeController {
	
	@Autowired
	private ProductService productService;

	@GetMapping
	public String home(Model model) {
		Pageable pageable = PageRequest.of(0, 9);
		model.addAttribute("products", productService.getAllProducts(pageable));
		
		return "index";
	}
	
	
	
}
