package com.example.expenseTracker.repository;

import com.example.expenseTracker.model.ItemCategory;
import com.example.expenseTracker.model.ItemSubcategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemSubcategoryRepository extends JpaRepository<ItemSubcategory, Long> {
    List<ItemSubcategory> findByCategory(ItemCategory category);
    List<ItemSubcategory> findByNameContainsIgnoreCase(String name);
    List<ItemSubcategory> findByNameContainsIgnoreCaseAndCategory(String name, ItemCategory category);
}
