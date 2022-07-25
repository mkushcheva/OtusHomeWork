package ru.otus.salebooks.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.salebooks.dto.BookSaleDto;
import ru.otus.salebooks.repository.SaleRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SaleServiceImpl implements SaleService {
    private final SaleRepository saleRepository;

    @Override
    @HystrixCommand(commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "3000")},
            fallbackMethod = "defaultBookFromMybook")
    public List<BookSaleDto> getAllSales() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            System.out.println(Arrays.toString(e.getStackTrace()));
        }

        return saleRepository.findAll().stream()
                .map(s -> new BookSaleDto(s.getTitle(), s.getSaleDate(), s.getQuantity(), s.getCost(), 0L))
                .collect(Collectors.toList());
    }

    private List<BookSaleDto> defaultBookFromMybook() {
        System.out.println("Сервис SaleService не доступен, выведем значение по умолчанию");

        List<BookSaleDto> bookDtoList = new ArrayList<>();
        bookDtoList.add(BookSaleDto.builder()
                .title("Сервис SaleService не доступен")
                .build());

        return bookDtoList;
    }
}
