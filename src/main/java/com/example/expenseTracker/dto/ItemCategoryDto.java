package com.example.expenseTracker.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemCategoryDto {
    private Long id;
    private String name;
    private List<ItemSubcategoryDto> subcategories;
    private String notes;
}
