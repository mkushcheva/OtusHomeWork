package ru.diasoft.library.rest.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.diasoft.library.rest.dto.GenreDto;
import ru.diasoft.library.service.GenreService;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class GenreController {
    private final GenreService genreService;

    @GetMapping("/genre")
    public List<GenreDto> getAllGenres() {
        return genreService.getAllGenreDto();
    }

    @GetMapping("/genre/{id}")
    public GenreDto getllGenreById(@PathVariable("id") long id) {
        return genreService.getGenreDtoById(id);
    }

    @PostMapping("/genre")
    public GenreDto createNewGenre(@RequestBody GenreDto dto) {
        return genreService.createNewGenreDto(dto);
    }

    @DeleteMapping("/genre/{id}")
    public void deleteById(@PathVariable("id") long id) {
        genreService.deleteById(id);
    }
}
