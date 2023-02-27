package com.example.expenseTracker.dto.request;

import com.example.expenseTracker.dto.base.ItemSubcategoryDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemSubcategoryRequest extends ItemSubcategoryDto {
    private String categoryId;
}
