package com.example.expenseTracker.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "generator_itemcategory")
    @SequenceGenerator(name="generator_itemcategory", sequenceName = "sequence_itemcategory")
    private Long id;
    @NotBlank
    @Column(unique = true, length = 100)
    private String name;
    @Column(columnDefinition = "text")
    private String notes;
}
