package com.mycompany.store.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mycompany.store.model.Order;

public interface OrderRepository extends JpaRepository<Order,Long>{

}
