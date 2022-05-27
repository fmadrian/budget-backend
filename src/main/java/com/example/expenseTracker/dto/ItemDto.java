package com.example.expenseTracker.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemDto {
    private Long id;
    private ItemSubcategoryDto subcategory;
    private BigDecimal total;
    private String notes;
}
