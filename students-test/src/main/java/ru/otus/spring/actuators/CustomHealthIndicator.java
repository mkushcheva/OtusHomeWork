package ru.otus.spring.actuators;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.boot.actuate.health.Status;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class CustomHealthIndicator implements HealthIndicator {
    private final Random random = new Random();

    @Override
    public Health health() {
        int countConnection = random.nextInt();

        if (countConnection > 30) {
            return Health.down()
                    .status(Status.DOWN)
                    .withDetail("message", "Работа остановлена, превышение лицензии!")
                    .build();
        } else {
            return Health.up().withDetail("message", "Работает, лицензия не превышена!").build();
        }
    }
}
