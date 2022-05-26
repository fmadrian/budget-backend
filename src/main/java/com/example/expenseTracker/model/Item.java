package com.example.expenseTracker.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @OneToOne
    @JoinColumn(name = "subcategoryId", referencedColumnName = "id")
    private ItemSubcategory subcategory;

    @Column(nullable = false)
    private BigDecimal total;

    @Lob
    private String notes;

    @Column(nullable = false)
    private boolean deleted;
}
