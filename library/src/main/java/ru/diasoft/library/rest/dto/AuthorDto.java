package ru.diasoft.library.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Data
@Builder
public class AuthorDto {
    private long id;
    private String name;
}
