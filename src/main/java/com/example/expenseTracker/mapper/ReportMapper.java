package com.example.expenseTracker.mapper;

import com.example.expenseTracker.dto.request.ItemRequest;
import com.example.expenseTracker.dto.request.ReportRequest;
import com.example.expenseTracker.dto.response.ItemResponse;
import com.example.expenseTracker.dto.response.ReportResponse;
import com.example.expenseTracker.model.Item;
import com.example.expenseTracker.model.Report;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Mapper(componentModel = "spring")
public abstract class ReportMapper {
    @Autowired
    private ItemMapper itemMapper;
    @Mapping(target = "items",expression = "java(mapItemsToEntity(reportRequest.getItems()))")
    public abstract Report mapToEntity(ReportRequest reportRequest);
    @Mapping(target = "items",expression = "java(mapItemsToDto(report.getItems()))")
    public abstract ReportResponse mapToDto(Report report);

    List<Item> mapItemsToEntity(List<ItemRequest> items){
        // Maps every item request into a item entity.
        return items.stream().map((item)-> itemMapper.mapToEntity(item)).toList();
    }
    List<ItemResponse> mapItemsToDto(List<Item> items){
        // Maps every item entity into a item request.
        return items.stream().map((item)->itemMapper.mapToDto(item)).toList();
    }
}
