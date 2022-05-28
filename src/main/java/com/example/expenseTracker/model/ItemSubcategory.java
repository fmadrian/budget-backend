package com.example.expenseTracker.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class ItemSubcategory {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @OneToOne
    @JoinColumn(name = "categoryId", referencedColumnName = "id")
    private ItemCategory category;
    @NotBlank
    @Column(unique = true, length = 100)
    private String name;
    @Column(columnDefinition = "text")
    private String notes;
}
