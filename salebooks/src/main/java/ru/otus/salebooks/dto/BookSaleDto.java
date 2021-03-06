package ru.otus.salebooks.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@RequiredArgsConstructor
public class BookSaleDto {
    private final String title;
    private final LocalDate saleDate;
    private final int quantity;
    private final BigDecimal cost;
    private long balance;
}
