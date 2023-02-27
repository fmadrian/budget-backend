package com.example.expenseTracker.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

@Document
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Report {
    @Id
    private String id;
    private String name;
    private String notes;
    private Instant date;
    // @OneToMany(fetch = FetchType.LAZY)
    // @JoinColumn(name = "reportId", referencedColumnName = "id")
    // @DocumentReference(lazy = true)
    @DBRef
    private List<Item> items;

    private BigDecimal expenses;
    private BigDecimal income;
    private BigDecimal total;
}
