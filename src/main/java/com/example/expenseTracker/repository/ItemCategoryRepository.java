package com.example.expenseTracker.repository;

import com.example.expenseTracker.model.ItemCategory;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemCategoryRepository extends MongoRepository<ItemCategory, String> {
    List<ItemCategory> findByNameContainsIgnoreCase(String name);
}
