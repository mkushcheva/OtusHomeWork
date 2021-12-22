package ru.diasoft.library.rest.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.diasoft.library.domain.Author;
import ru.diasoft.library.exception.NotFoundException;
import ru.diasoft.library.repository.AuthorRepositoryJpa;
import ru.diasoft.library.rest.dto.AuthorDto;
import ru.diasoft.library.rest.mapper.AuthorMapper;
import ru.diasoft.library.utils.MessageSourceUtils;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
public class AuthorController {
    private final AuthorRepositoryJpa repository;
    private final AuthorMapper mapper;
    private final MessageSourceUtils messageSource;

    @GetMapping("/author")
    public List<AuthorDto> getAllAuthors() {
        return repository.findAll().stream()
                .map(mapper::authorDomainToAuthorDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/author/{id}")
    public AuthorDto getllAuthorById(@PathVariable("id") long id) {
        Author author = repository.findById(id).orElseThrow(
                () -> new NotFoundException(messageSource.getMessage("author.notFound")));
        return mapper.authorDomainToAuthorDto(author);
    }

    @PostMapping("/author")
    public AuthorDto createNewAuthor(@RequestBody AuthorDto dto) {
        Author author = mapper.authorDtoToAuthorDomain(dto);
        return mapper.authorDomainToAuthorDto(repository.save(author));
    }

    @DeleteMapping("/author/{id}")
    public void deleteById(@PathVariable("id") long id) {
        repository.deleteById(id);
    }
}
