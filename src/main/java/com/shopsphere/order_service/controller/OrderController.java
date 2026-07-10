package com.shopsphere.order_service.controller;

import org.springframework.web.bind.annotation.RestController;

import com.shopsphere.order_service.entity.Order;
import com.shopsphere.order_service.service.OrderService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("/api/orders")
public class OrderController {
    @Autowired
    private OrderService orderService;    

    @PostMapping
    public ResponseEntity<Order> createOrder(@RequestBody Order order) {
        Order created  = orderService.createOrder(order);
        return ResponseEntity.ok(created);
    }
    
}
