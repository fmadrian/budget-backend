package com.example.expenseTracker.dto.request;

import com.example.expenseTracker.dto.base.ReportDto;
import com.example.expenseTracker.dto.response.ItemResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReportRequest extends ReportDto {
    private List<ItemRequest> items;
}
