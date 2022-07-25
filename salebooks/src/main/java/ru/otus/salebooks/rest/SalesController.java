package ru.otus.salebooks.rest;

import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import ru.otus.salebooks.dto.BookDto;
import ru.otus.salebooks.dto.BookSaleDto;
import ru.otus.salebooks.exception.MyBooksConnectionException;
import ru.otus.salebooks.messaging.MessageProducer;
import ru.otus.salebooks.service.SaleService;

import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/salebooks")
@RequiredArgsConstructor
public class SalesController {
    private final SaleService saleService;
    private final RestTemplate rest;
    private String token;
    @Value("${mybook.url}")
    private String url;
    @Value("${mybook.user}")
    private String userName;
    @Value("${mybook.pass}")
    private String userPass;

    private final MessageProducer messageProducer;

    @PostMapping("/sale")
    public void addSale(@RequestBody BookSaleDto bookSale) {
        System.out.println("Продаем книгу :" + bookSale);
        messageProducer.send(bookSale);
    }

    @GetMapping("/book-sales")
    public List<BookSaleDto> getBookSales() {
        List<BookSaleDto> bookSales = saleService.getAllSales();
        Map<String, BookDto> titleBookMap = getTitleBookMap();

        for (BookSaleDto bookSale : bookSales)
            bookSale.setBalance(titleBookMap.get(bookSale.getTitle()).getBalance());

        return bookSales;
    }

    private Map<String, BookDto> getTitleBookMap() {
        List<BookDto> books = getBooks();
        Map<String, BookDto> map = new HashMap<>();
        for (BookDto book : books)
            map.put(book.getTitle(), book);
        return map;
    }

    @ExceptionHandler(MyBooksConnectionException.class)
    public ResponseEntity<String> handleBookReviewNotFound(MyBooksConnectionException ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }

    private List<BookDto> getBooks() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + getToken());
        try {
            ResponseEntity<List<BookDto>> response = rest.exchange(url + "/book",
                    HttpMethod.GET, new HttpEntity<String>(headers),
                    new ParameterizedTypeReference<List<BookDto>>() {

                    });

            return response.getStatusCode().equals(HttpStatus.OK) && (response.getBody() != null) ?
                    response.getBody() :
                    Collections.emptyList();

        } catch (Exception e) {
            throw new MyBooksConnectionException("Error getting a book list from MyBooks", e);
        }
    }

    private String getToken() {
        if (token == null) {
            String auth = userName + ":" + userPass;
            HttpHeaders headers = new HttpHeaders();
            byte[] encodedAuth = Base64.encodeBase64(auth.getBytes(StandardCharsets.US_ASCII));
            headers.set("Authorization", "Basic " + new String(encodedAuth));
            HttpEntity authEntity = new HttpEntity<>(headers);
            try {
                ResponseEntity response = rest.exchange(url + "/token",
                        HttpMethod.POST, authEntity, String.class);
                if (response.getStatusCode().equals(HttpStatus.OK) && (response.getBody() != null))
                    token = response.getBody().toString();
            } catch (Exception e) {
                throw new MyBooksConnectionException("Error getting a token from MyBooks", e);
            }
        }
        return token;
    }
}
