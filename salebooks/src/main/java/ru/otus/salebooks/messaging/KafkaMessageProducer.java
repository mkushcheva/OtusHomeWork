package ru.otus.salebooks.messaging;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import ru.otus.salebooks.dto.BookSaleDto;

@Service
@RequiredArgsConstructor
public class KafkaMessageProducer implements MessageProducer {
    private final KafkaTemplate<String, BookSaleDto> kafkaTemplate;

    @Value(value = "${salebooks.kafka-topic-name}")
    private String topicName;

    @SneakyThrows
    @Override
    public void send(BookSaleDto sale) {
        kafkaTemplate.send(topicName, sale);
    }
}
