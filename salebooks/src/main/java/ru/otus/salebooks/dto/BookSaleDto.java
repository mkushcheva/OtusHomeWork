package ru.otus.salebooks.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class BookSaleDto {
    private String title;
    private LocalDate saleDate;
    private int quantity;
    private BigDecimal cost;
    private long balance;
}
