package ru.diasoft.library.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.diasoft.library.domain.Genre;
import ru.diasoft.library.exception.NotFoundException;
import ru.diasoft.library.repository.GenreRepository;
import ru.diasoft.library.rest.dto.GenreDto;
import ru.diasoft.library.rest.mapper.GenreMapper;
import ru.diasoft.library.utils.MessageSourceUtils;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class GenreServiceImpl implements GenreService {
    private final GenreRepository genreRepository;
    private final GenreMapper mapper;
    private final MessageSourceUtils messageSource;

    @Override
    public Genre getByName(String name) {
        return genreRepository.findByName(name).orElse(null);
    }

    @Override
    @Transactional
    public Genre create(String name) {
        return genreRepository.save(new Genre(0, name));
    }

    @Override
    public void deleteById(long id) {
        genreRepository.deleteById(id);
    }

    @Override
    public List<GenreDto> getAllGenreDto() {
        return genreRepository.findAll().stream()
                .map(mapper::genreDomainToGenreDto)
                .collect(Collectors.toList());
    }

    @Override
    public GenreDto getGenreDtoById(long id) {
        Genre genre = genreRepository.findById(id).orElseThrow(
                () -> new NotFoundException(messageSource.getMessage("genre.notFound")));
        return mapper.genreDomainToGenreDto(genre);
    }

    @Override
    public GenreDto createNewGenreDto(GenreDto dto) {
        Genre genre = mapper.genreDtoToGenreDomain(dto);
        return mapper.genreDomainToGenreDto(genreRepository.save(genre));
    }
}
