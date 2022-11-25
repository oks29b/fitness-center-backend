package com.example.servingwebcontent.model.repository;

import com.example.servingwebcontent.model.entity.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderDetailsRepository extends JpaRepository<OrderDetail, Long> {
}
