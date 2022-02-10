package ru.diasoft.library.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@AllArgsConstructor
@Data
@Builder
public class BookDto {
    private long id;
    private String title;

    private String authorName;
    private String genreName;

    private List<String> comments;
}
