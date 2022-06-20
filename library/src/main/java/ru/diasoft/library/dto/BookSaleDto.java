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
    @JsonProperty("title")
    private String title;

    @JsonProperty("saleDate")
    private LocalDate saleDate;

    @JsonProperty("quantity")
    private int quantity;

    @JsonProperty("cost")
    private BigDecimal cost;

    @JsonProperty("balance")
    private long balance;
}
