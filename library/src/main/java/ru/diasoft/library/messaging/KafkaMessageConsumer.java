package ru.diasoft.library.messaging;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import ru.diasoft.library.domain.Book;
import ru.diasoft.library.dto.BookSaleDto;
import ru.diasoft.library.repository.BookRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class KafkaMessageConsumer implements MessageConsumer {
    private final BookRepository bookRepository;

    @KafkaListener(
            topics = "${library.kafka-topic-name}",
            groupId = "${spring.kafka.consumer.group-id}",
            containerFactory = "kafkaListenerContainerFactory")
    @Override
    public void listen(BookSaleDto sale) {
        Optional<Book> book = bookRepository.findByTitle(sale.getTitle());

        if (book.isPresent())
            bookRepository.deleteById(book.get().getId());

        System.out.println("Книга продана :" + sale.getTitle());
    }
}
