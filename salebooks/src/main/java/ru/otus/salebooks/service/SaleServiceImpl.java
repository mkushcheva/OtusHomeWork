package ru.otus.salebooks.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.salebooks.dto.BookSaleDto;
import ru.otus.salebooks.repository.SaleRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SaleServiceImpl implements SaleService {
    private final SaleRepository saleRepository;

    @Override
    public List<BookSaleDto> getAllSales() {
        return saleRepository.findAll().stream()
                .map(s -> new BookSaleDto(s.getTitle(), s.getSaleDate(), s.getQuantity(), s.getCost()))
                .collect(Collectors.toList());
    }
}
