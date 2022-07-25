package ru.diasoft.library.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BookSaleDto {
    private String title;
    private LocalDate saleDate;
    private int quantity;
    private BigDecimal cost;
    private long balance;
}
