package com.example.app.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.app.model.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order>  findAllByUsersId(Long userId);
}
