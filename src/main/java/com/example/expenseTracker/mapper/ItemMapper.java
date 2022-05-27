package com.example.expenseTracker.mapper;

import com.example.expenseTracker.dto.request.ItemRequest;
import com.example.expenseTracker.dto.response.ItemResponse;
import com.example.expenseTracker.exception.ItemSubcategoryNotFoundException;
import com.example.expenseTracker.model.Item;
import com.example.expenseTracker.model.ItemSubcategory;
import com.example.expenseTracker.repository.ItemRepository;
import com.example.expenseTracker.repository.ItemSubcategoryRepository;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring")
public abstract class ItemMapper {
    @Autowired
    private ItemSubcategoryRepository itemSubcategoryRepository;
    @Autowired
    ItemSubcategoryMapper itemSubcategoryMapper;
    @Mapping(target = "subcategory", expression = "java(getSubcategory(itemRequest.getSubcategoryId()))")
    public abstract Item mapToEntity(ItemRequest itemRequest);
    @Mapping(target = "subcategory", expression = "java(itemSubcategoryMapper.mapToDto(item.getSubcategory()))")
    public abstract ItemResponse mapToDto(Item item);

    ItemSubcategory getSubcategory(Long subcategoryId){
        if(subcategoryId != null){
            try{
                return itemSubcategoryRepository.findById(subcategoryId).orElseThrow(()-> new ItemSubcategoryNotFoundException(subcategoryId));
            }catch (ItemSubcategoryNotFoundException e){
                // Do nothing. Assign it a null.
            }
        }
        return null;
    }

}
