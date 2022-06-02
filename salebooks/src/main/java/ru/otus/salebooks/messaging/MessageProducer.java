package ru.otus.salebooks.messaging;

import ru.otus.salebooks.dto.BookSaleDto;

public interface MessageProducer {
    void send(BookSaleDto sale);
}
