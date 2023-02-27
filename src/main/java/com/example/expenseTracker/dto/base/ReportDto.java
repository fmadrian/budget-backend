package com.example.expenseTracker.dto.base;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReportDto {
    private String id;
    private String name;
    private String date;
}
