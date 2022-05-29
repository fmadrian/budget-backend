package com.example.expenseTracker.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Report {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "generator_report")
    @SequenceGenerator(name="generator_report", sequenceName = "sequence_report")
    private Long id;
    @NotBlank
    @Column(length = 150)
    private String name;
    @Column(columnDefinition = "text")
    private String notes;
    @Column(nullable = false)
    private Instant date;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "reportId", referencedColumnName = "id")
    private List<Item> items;

    @Column(nullable = false)
    private BigDecimal expenses;
    @Column(nullable = false)
    private BigDecimal income;
    @Column(nullable = false)
    private BigDecimal total;
}
