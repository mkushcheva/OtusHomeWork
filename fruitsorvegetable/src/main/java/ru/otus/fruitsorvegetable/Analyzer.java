package ru.otus.fruitsorvegetable;

import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;
import ru.otus.fruitsorvegetable.domain.Plant;
import ru.otus.fruitsorvegetable.domain.Answer;

@MessagingGateway
public interface Analyzer {

    @Gateway(requestChannel = "plantChannel", replyChannel = "answerChannel")
    Answer process(Plant Plant);
}
