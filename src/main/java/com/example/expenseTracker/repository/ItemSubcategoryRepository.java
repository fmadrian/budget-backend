package com.example.expenseTracker.repository;

import com.example.expenseTracker.model.ItemSubcategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemSubcategoryRepository extends JpaRepository<ItemSubcategory, Long> {
}
