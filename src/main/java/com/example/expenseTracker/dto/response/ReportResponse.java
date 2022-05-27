package com.example.expenseTracker.dto.response;

import com.example.expenseTracker.dto.base.ReportDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReportResponse extends ReportDto {
    private List<ItemResponse> items;
    private Long total;
}
