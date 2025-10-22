package com.example.app.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.app.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
