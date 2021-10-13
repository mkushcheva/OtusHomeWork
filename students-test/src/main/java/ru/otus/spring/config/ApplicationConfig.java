package ru.otus.spring.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.io.File;

@ConfigurationProperties(prefix = "testing")
@Component
@Getter
@Setter
public class ApplicationConfig {
    private File fileName;
    private File filename_ru_RU;
    private int count;
}
