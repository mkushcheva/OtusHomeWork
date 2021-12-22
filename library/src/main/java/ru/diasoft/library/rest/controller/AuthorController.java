package ru.diasoft.library.rest.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.diasoft.library.rest.dto.AuthorDto;
import ru.diasoft.library.service.AuthorService;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class AuthorController {
    private final AuthorService authorService;

    @GetMapping("/author")
    public List<AuthorDto> getAllAuthors() {
        return authorService.getAllAuthorDto();
    }

    @GetMapping("/author/{id}")
    public AuthorDto getAuthorById(@PathVariable("id") long id) {
        return authorService.getAuthorDtoByID(id);
    }

    @PostMapping("/author")
    public AuthorDto createNewAuthor(@RequestBody AuthorDto dto) {
        return authorService.createNewAuthorDto(dto);
    }

    @DeleteMapping("/author/{id}")
    public void deleteById(@PathVariable("id") long id) {
        authorService.deleteById(id);
    }
}
