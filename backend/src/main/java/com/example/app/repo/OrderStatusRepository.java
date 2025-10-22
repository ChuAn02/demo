package com.example.app.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.app.model.OrderStatus;

public interface OrderStatusRepository extends JpaRepository<OrderStatus, Long> {
}