package com.example.expenseTracker.service;

import com.example.expenseTracker.dto.request.ItemSubcategoryRequest;
import com.example.expenseTracker.dto.response.ItemSubcategoryResponse;
import com.example.expenseTracker.exception.ItemCategoryNotFoundException;
import com.example.expenseTracker.exception.ItemSubcategoryInUseException;
import com.example.expenseTracker.exception.ItemSubcategoryNotFoundException;
import com.example.expenseTracker.mapper.ItemSubcategoryMapper;
import com.example.expenseTracker.model.ItemCategory;
import com.example.expenseTracker.model.ItemSubcategory;
import com.example.expenseTracker.repository.ItemCategoryRepository;
import com.example.expenseTracker.repository.ItemRepository;
import com.example.expenseTracker.repository.ItemSubcategoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ItemSubcategoryService {
    private final ItemSubcategoryMapper itemSubcategoryMapper;
    private final ItemSubcategoryRepository itemSubcategoryRepository;
    private final ItemCategoryRepository itemCategoryRepository;
    private final ItemRepository itemRepository;
    public ItemSubcategoryResponse create(ItemSubcategoryRequest itemSubcategoryRequest){
        // Map the request, create the subcategory and then return it.
        ItemSubcategory itemSubcategory = itemSubcategoryRepository.save(itemSubcategoryMapper.mapToEntity(itemSubcategoryRequest));
        return itemSubcategoryMapper.mapToDto(itemSubcategory);
    }
    public ItemSubcategoryResponse update(ItemSubcategoryRequest itemSubcategoryRequest){
        // Verify that the subcategory exists before updating it.
        itemSubcategoryRepository.findById(itemSubcategoryRequest.getId()).orElseThrow(()-> new ItemSubcategoryNotFoundException(itemSubcategoryRequest.getId()));
        // Map the request, create the subcategory and then return it.
        ItemSubcategory itemSubcategory = itemSubcategoryRepository.save(itemSubcategoryMapper.mapToEntity(itemSubcategoryRequest));
        return itemSubcategoryMapper.mapToDto(itemSubcategory);
    }
    public void delete(Long id){
        // Find the subcategory.
        ItemSubcategory itemSubcategory = itemSubcategoryRepository.findById(id).orElseThrow(()-> new ItemSubcategoryNotFoundException(id));
        // Can't delete subcategory that are being used by an item.
        if(!itemRepository.findBySubcategory(itemSubcategory).isEmpty()){
            throw new ItemSubcategoryInUseException(itemSubcategory);
        };
        // Delete it.
        itemSubcategoryRepository.delete(itemSubcategory);
    }
    public List<ItemSubcategoryResponse> get(String name, Long categoryId){
        List <ItemSubcategory> queryResults = null;
        ItemCategory itemCategory = null;
        // Search the category, if there's any.
        if(categoryId != null){
            itemCategory = itemCategoryRepository.findById(categoryId)
                    .orElseThrow(()-> new ItemCategoryNotFoundException(categoryId));
        }
        // Query section.
        if(name != null && !name.isEmpty()){
            // Upper case and remove spaces.
            name = name.toUpperCase(Locale.ROOT).trim();
            if(itemCategory != null){
                // Name and category query.
                queryResults = itemSubcategoryRepository.findByNameContainsIgnoreCaseAndCategory(name,itemCategory);
            }else{
                // Only by name.
                queryResults = itemSubcategoryRepository.findByNameContainsIgnoreCase(name);
            }
        }else if(itemCategory != null){
            // By category.
            queryResults = itemSubcategoryRepository.findByCategory(itemCategory);
        }
        else {
            // Return all.
            queryResults = itemSubcategoryRepository.findAll();
        }
        // Map and return the results.
        return queryResults.stream()
                .map(itemSubcategoryMapper :: mapToDto)
                .collect(Collectors.toList());
    }

    public ItemSubcategoryResponse getByName(String name) {
        ItemSubcategory itemSubcategory = itemSubcategoryRepository.findByNameIgnoreCase(name)
                .orElseThrow(()-> new ItemSubcategoryNotFoundException());

        return itemSubcategoryMapper.mapToDto(itemSubcategory);
    }
}
