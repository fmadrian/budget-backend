package com.example.expenseTracker.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

@Document
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemSubcategory {
    @Id
    private String id;
    //@DocumentReference(lazy=true)
    @DBRef
    private ItemCategory category;
    private String name;
    private String notes;
}
