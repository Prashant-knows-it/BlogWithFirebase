package com.example.LoginPlusSecurity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.LoginPlusSecurity.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    
    @Query("SELECT c.name FROM Category c WHERE c.id = :id")
    String getCategoryNameById(@Param("id") Long id);
}
