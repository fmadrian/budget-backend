package com.example.expenseTracker.mapper;

import com.example.expenseTracker.dto.base.ItemCategoryDto;
import com.example.expenseTracker.model.ItemCategory;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ItemCategoryMapper {
    public abstract ItemCategory mapToEntity(ItemCategoryDto itemCategoryDto);
    public abstract ItemCategoryDto mapToDto(ItemCategory itemCategory);
}
