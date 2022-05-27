package com.example.expenseTracker.dto.response;

import com.example.expenseTracker.dto.base.ItemDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemResponse extends ItemDto {
    private ItemSubcategoryResponse subcategory;
}
