package com.example.expenseTracker.mapper;

import com.example.expenseTracker.dto.base.ItemCategoryDto;
import com.example.expenseTracker.model.ItemCategory;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.Locale;

@Mapper(componentModel = "spring")
public abstract class ItemCategoryMapper {
    Locale locale = Locale.ROOT;

    @Mapping(target="name",expression = "java(itemCategoryDto.getName().toUpperCase(locale).trim())")
    public abstract ItemCategory mapToEntity(ItemCategoryDto itemCategoryDto);
    public abstract ItemCategoryDto mapToDto(ItemCategory itemCategory);
}
