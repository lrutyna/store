package com.mycompany.store.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.mycompany.store.model.Order;
import com.mycompany.store.repository.OrderRepository;
import com.mycompany.store.service.AccountService;


@Controller
public class OrderController {
	
	@Autowired
	private AccountService accountService;
	
	@Autowired
	private OrderRepository orderRepository;
	
	
	@GetMapping("/login-success")
	public String loginSuccess() {
		Order order = new Order();
		order.setAccount(accountService.getCurrentUser());
		
		orderRepository.save(order);
		
		return "order/cart";
	}

	@GetMapping("/cart")
	public String addNewOrder() {
		
		
		return "order/cart";
	}
	
	@PostMapping("/new-order")
	public String newOrderResult() {
		
		return "order/orderResult";
	}
}
