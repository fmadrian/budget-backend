package com.example.expenseTracker.mapper;

import com.example.expenseTracker.dto.request.ItemSubcategoryRequest;
import com.example.expenseTracker.dto.response.ItemSubcategoryResponse;
import com.example.expenseTracker.exception.ItemCategoryNotFoundException;
import com.example.expenseTracker.model.ItemCategory;
import com.example.expenseTracker.model.ItemSubcategory;
import com.example.expenseTracker.repository.ItemCategoryRepository;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Locale;

@Mapper(componentModel="spring")
public abstract class ItemSubcategoryMapper {
    @Autowired
    private ItemCategoryRepository itemCategoryRepository;
    @Autowired
    ItemCategoryMapper itemCategoryMapper;
    Locale locale = Locale.ROOT;

    @Mapping(target = "name", expression = "java(itemSubcategoryRequest.getName().toUpperCase(locale).trim())")
    @Mapping(target = "category", expression = "java(getCategory(itemSubcategoryRequest.getCategoryId()))")
    public abstract ItemSubcategory mapToEntity(ItemSubcategoryRequest itemSubcategoryRequest);
    @Mapping(target = "category", expression = "java(itemCategoryMapper.mapToDto(itemSubcategory.getCategory()))")
    public abstract ItemSubcategoryResponse mapToDto(ItemSubcategory itemSubcategory);

    ItemCategory getCategory(String categoryId){
        if(categoryId != null) {
            try {
                return itemCategoryRepository.findById(categoryId).orElseThrow(() -> new ItemCategoryNotFoundException(categoryId));
            }catch (ItemCategoryNotFoundException e){
                // Do nothing, assign it a null.
            }
        }
        return null;
    }
}
