package com.example.expenseTracker.repository;

import com.example.expenseTracker.model.Item;
import com.example.expenseTracker.model.ItemSubcategory;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository extends MongoRepository<Item, String> {
    List<Item> findBySubcategory(ItemSubcategory itemSubcategory);
}
