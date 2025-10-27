package com.example.app.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.app.model.CartItem;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    List<CartItem> findAllByUserId(Long id);

    void deleteAllByUserId(Long id);

    CartItem findCartItemByProductIdAndUserId(Long productId, Long UserId);
}