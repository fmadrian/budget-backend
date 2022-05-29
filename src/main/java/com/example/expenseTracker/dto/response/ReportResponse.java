package com.example.expenseTracker.dto.response;

import com.example.expenseTracker.dto.base.ReportDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReportResponse extends ReportDto {
    private BigDecimal income;
    private BigDecimal expenses;
    private BigDecimal total;
    private List<ItemResponse> items;
}
