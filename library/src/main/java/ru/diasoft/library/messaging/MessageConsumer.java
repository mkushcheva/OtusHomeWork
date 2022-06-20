package ru.diasoft.library.messaging;

import ru.diasoft.library.dto.BookSaleDto;

public interface MessageConsumer {
    void listen(BookSaleDto sale);
}
