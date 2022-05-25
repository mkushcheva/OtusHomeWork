package ru.otus.salebooks.service;

import ru.otus.salebooks.dto.BookSaleDto;

import java.util.List;

public interface SaleService {
    List<BookSaleDto> getAllSales();
}
