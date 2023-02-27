package com.example.expenseTracker.dto.request;

import com.example.expenseTracker.dto.base.ItemDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemRequest extends ItemDto {
    private String subcategoryId;
}
