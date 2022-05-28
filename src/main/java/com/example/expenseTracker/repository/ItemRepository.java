package com.example.expenseTracker.repository;

import com.example.expenseTracker.model.Item;
import com.example.expenseTracker.model.ItemSubcategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
    List<Item> findBySubcategory(ItemSubcategory itemSubcategory);
}
