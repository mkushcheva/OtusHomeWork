package ru.otus.spring.dao;

import org.springframework.core.io.Resource;

public class QuestionResource {
    private Resource resource;

    public void setResource(Resource resource) {
        this.resource = resource;
    }

    public Resource getResource(){
        return resource;
    }
}
