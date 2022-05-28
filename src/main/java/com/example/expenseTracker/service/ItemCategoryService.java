package com.example.expenseTracker.service;

import com.example.expenseTracker.dto.base.ItemCategoryDto;
import com.example.expenseTracker.exception.ItemCategoryNotFoundException;
import com.example.expenseTracker.mapper.ItemCategoryMapper;
import com.example.expenseTracker.model.ItemCategory;
import com.example.expenseTracker.model.ItemSubcategory;
import com.example.expenseTracker.repository.ItemCategoryRepository;
import com.example.expenseTracker.repository.ItemSubcategoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ItemCategoryService {
    private final ItemCategoryMapper itemCategoryMapper;
    private final ItemCategoryRepository itemCategoryRepository;
    private final ItemSubcategoryRepository itemSubcategoryRepository;

    @Transactional
    public ItemCategoryDto create(ItemCategoryDto itemCategoryRequest){
        // Map the request, create the category and then return it.
        ItemCategory itemCategory = itemCategoryRepository.save(itemCategoryMapper.mapToEntity(itemCategoryRequest));
        return itemCategoryMapper.mapToDto(itemCategory);
    }
    @Transactional
    public ItemCategoryDto update(ItemCategoryDto itemCategoryRequest){
        // Map the request, update the category and then return it.
        ItemCategory itemCategory = itemCategoryRepository.save(itemCategoryMapper.mapToEntity(itemCategoryRequest));
        return itemCategoryMapper.mapToDto(itemCategory);
    }
    @Transactional
    public void delete(Long id){
        // Find the subcategory.
        ItemCategory itemCategory = itemCategoryRepository.findById(id).orElseThrow(()-> new ItemCategoryNotFoundException(id));
        // Get every element that uses this subcategory, and change them to null.
        List<ItemSubcategory> subcategories = itemSubcategoryRepository.findByCategory(itemCategory)
                .stream().map((subcategory)->{
                    subcategory.setCategory(null);
                    return subcategory;
                }).collect(Collectors.toList());
        itemSubcategoryRepository.saveAll(subcategories);
        // Delete the category.
        itemCategoryRepository.delete(itemCategory);
    }
    @Transactional(readOnly = true)
    public List<ItemCategoryDto> get(String name){
        List<ItemCategory> queryResults = null;
        if(name != null && !name.isEmpty()){
            queryResults = itemCategoryRepository.findByNameContainsIgnoreCase(name);
        }else{
            queryResults = itemCategoryRepository.findAll();
        }
        return queryResults.stream()
                .map(itemCategoryMapper :: mapToDto)
                .collect(Collectors.toList());
    }
}
