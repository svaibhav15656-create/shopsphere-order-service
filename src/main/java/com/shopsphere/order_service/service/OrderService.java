package com.shopsphere.order_service.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.shopsphere.order_service.entity.Order;
import com.shopsphere.order_service.entity.OrderStatus;
import com.shopsphere.order_service.event.OrderEvent;
import com.shopsphere.order_service.repository.OrderRepository;




@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private KafkaTemplate<String, OrderEvent> kafkaTemplate;

    public Order createOrder(Order order){
        order.setStatus(OrderStatus.PENDING);
        order.setOrderDate(LocalDateTime.now());
        Order saveOrder = orderRepository.save(order);

        OrderEvent event = new OrderEvent(saveOrder.getId(),saveOrder.getProductId(),saveOrder.getQuantity());
        kafkaTemplate.send("order-events",event);
        return saveOrder;
    }

}
