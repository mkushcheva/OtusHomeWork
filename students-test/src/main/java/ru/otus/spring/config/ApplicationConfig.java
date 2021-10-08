package ru.otus.spring.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.io.File;

@ConfigurationProperties(prefix = "testing")
@Component
public class ApplicationConfig {
    private File fileName;
    private File filename_ru_RU;
    private int count;

    public File getFileName() {
        return fileName;
    }

    public void setFileName(File fileName) {
        this.fileName = fileName;
    }

    public File getFileName_ru_RU() {
        return filename_ru_RU;
    }

    public void setFileName_ru_RU(File fileName) {
        this.filename_ru_RU = fileName;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
