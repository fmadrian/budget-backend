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

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public abstract class ReportMapper {
    @Autowired
    private ItemMapper itemMapper;
    Locale locale = Locale.ROOT;
    @Mapping(target = "date", expression = "java(getInstantFromString(reportRequest.getDate()))")
    @Mapping(target = "name", expression = "java(reportRequest.getName().toUpperCase(locale).trim())")
    @Mapping(target = "items",expression = "java(mapItemsToEntity(reportRequest.getItems()))")
    @Mapping(target = "total",expression = "java(getTotal(reportRequest))")
    public abstract Report mapToEntity(ReportRequest reportRequest);
    @Mapping(target = "date", expression = "java(getStringFromInstant(report.getDate()))")
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
    Instant getInstantFromString(String date){
        return Instant.parse(date);
    }
    String getStringFromInstant(Instant instant){
        return instant.toString();
    }
    // Gets the total from the objects.
    BigDecimal getTotal(ReportRequest reportRequest){
        return reportRequest.getItems().stream()
                .map((item)-> item.getTotal()).reduce(BigDecimal.ZERO, BigDecimal::add);

    }
}
