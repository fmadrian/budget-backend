package com.example.expenseTracker.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Objects;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "generator_item")
    @SequenceGenerator(name="generator_item", sequenceName = "sequence_item")
    private Long id;
    @OneToOne
    @JoinColumn(name = "subcategoryId", referencedColumnName = "id")
    private ItemSubcategory subcategory;
    @Column(nullable = false)
    private BigDecimal total;
    @Column(columnDefinition = "text")
    private String notes;
    @Column(nullable = false)
    private boolean income; // An item is income or an expense.
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return id.equals(item.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
