package com.example.expenseTracker.repository;

import com.example.expenseTracker.model.ItemCategory;
import com.example.expenseTracker.model.ItemSubcategory;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ItemSubcategoryRepository extends MongoRepository<ItemSubcategory, String> {
    List<ItemSubcategory> findByCategory(ItemCategory category);
    List<ItemSubcategory> findByNameContainsIgnoreCase(String name);
    List<ItemSubcategory> findByNameContainsIgnoreCaseAndCategory(String name, ItemCategory category);

    Optional<ItemSubcategory> findByNameIgnoreCase(String name);
}
