package ru.otus.salebooks.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookDto {
    private long id;
    private String title;
    private List<String> authors;
    private List<String> genres;
    private long balance;
}
