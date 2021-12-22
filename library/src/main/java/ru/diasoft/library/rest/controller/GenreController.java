package ru.diasoft.library.rest.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.diasoft.library.domain.Genre;
import ru.diasoft.library.exception.NotFoundException;
import ru.diasoft.library.repository.GenreRepository;
import ru.diasoft.library.rest.dto.GenreDto;
import ru.diasoft.library.rest.mapper.GenreMapper;
import ru.diasoft.library.utils.MessageSourceUtils;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
public class GenreController {
    private final GenreRepository repository;
    private final GenreMapper mapper;
    private final MessageSourceUtils messageSource;

    @GetMapping("/genre")
    public List<GenreDto> getAllGenres() {
        return repository.findAll().stream()
                .map(mapper::genreDomainToGenreDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/genre/{id}")
    public GenreDto getllGenreById(@PathVariable("id") long id) {
        Genre genre = repository.findById(id).orElseThrow(
                () -> new NotFoundException(messageSource.getMessage("genre.notFound")));
        return mapper.genreDomainToGenreDto(genre);
    }

    @PostMapping("/genre")
    public GenreDto createNewGenre(@RequestBody GenreDto dto) {
        Genre genre = mapper.genreDtoToGenreDomain(dto);
        return mapper.genreDomainToGenreDto(repository.save(genre));
    }

    @DeleteMapping("/genre/{id}")
    public void deleteById(@PathVariable("id") long id) {
        repository.deleteById(id);
    }
}
