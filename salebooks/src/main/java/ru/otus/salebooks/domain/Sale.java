package ru.otus.salebooks.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "sales")
public class Sale {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "title", nullable = false, unique = true)
    private String title;

    @Column(name = "sale_date", nullable = false, unique = false)
    private LocalDate saleDate;

    @Column(name = "quantity", nullable = false, unique = false)
    private int quantity;

    @Column(name = "cost", nullable = false, unique = false)
    private BigDecimal cost;
}
