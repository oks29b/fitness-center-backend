package com.example.servingwebcontent.model.repository;

import com.example.servingwebcontent.model.entity.Order;
import com.example.servingwebcontent.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {

    Order findOrderByUser(User user);
}
