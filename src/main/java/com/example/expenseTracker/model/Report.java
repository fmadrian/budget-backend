package com.example.expenseTracker.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Report {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @Column(length = 150)
    private String name;
    @Column(nullable = false)
    private boolean deleted;
    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "reportId", referencedColumnName = "id")
    private List<Item> items;
}
