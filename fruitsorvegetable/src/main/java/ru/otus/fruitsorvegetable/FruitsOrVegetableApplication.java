package ru.otus.fruitsorvegetable;

import org.apache.commons.lang3.RandomUtils;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.integration.channel.PublishSubscribeChannel;
import org.springframework.integration.channel.QueueChannel;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.dsl.MessageChannels;
import org.springframework.integration.dsl.Pollers;
import org.springframework.integration.scheduling.PollerMetadata;
import ru.otus.fruitsorvegetable.domain.Answer;
import ru.otus.fruitsorvegetable.domain.Plant;

@IntegrationComponentScan
@ComponentScan
@Configuration
@EnableIntegration
public class FruitsOrVegetableApplication {

    private static final String[] LIST_OF_PLANT = {
            "яблоко",
            "апельсин",
            "мандарин",
            "клубника",
            "помидор",
            "огурец",
            "капуста",
            "свекла"
    };

    @Bean
    public QueueChannel plantChannel() {
        return MessageChannels.queue(10).get();
    }

    @Bean
    public PublishSubscribeChannel answerChannel() {
        return MessageChannels.publishSubscribe().get();
    }

    @Bean(name = PollerMetadata.DEFAULT_POLLER)
    public PollerMetadata poller() {
        return Pollers.fixedRate(100).maxMessagesPerPoll(2).get();
    }

    @Bean
    public IntegrationFlow plantFlow() {
        return IntegrationFlows.from("plantChannel")
                .handle("analyzerService", "process")
                .channel("answerChannel")
                .get();
    }

    public static void main(String[] args) throws Exception {
        AbstractApplicationContext ctx = new AnnotationConfigApplicationContext(FruitsOrVegetableApplication.class);

        Analyzer analyzer = ctx.getBean(Analyzer.class);

        while (true) {
            Thread.sleep(1000);

            Plant plant = generateOrderItem();
            System.out.println("Растение: " + plant.getPlant());
            Answer answer = analyzer.process(plant);
            System.out.println(plant.getPlant() + " является : " + answer.getAnswer());
        }
    }

    private static Plant generateOrderItem() {
        return new Plant(LIST_OF_PLANT[RandomUtils.nextInt(0, LIST_OF_PLANT.length)]);
    }
}
