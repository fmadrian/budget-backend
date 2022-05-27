package com.example.expenseTracker.dto.response;

import com.example.expenseTracker.dto.base.ItemCategoryDto;
import com.example.expenseTracker.dto.base.ItemSubcategoryDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemSubcategoryResponse extends ItemSubcategoryDto {
    private ItemCategoryDto category;
}
