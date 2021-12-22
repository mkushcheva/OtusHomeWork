package ru.diasoft.library.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.diasoft.library.domain.Genre;
import ru.diasoft.library.repository.GenreRepositoryJpa;

@RequiredArgsConstructor
@Service
public class GenreServiceImpl implements GenreService {
    private final GenreRepositoryJpa genreRepository;

    @Override
    public Genre getByName(String name) {
        return genreRepository.findByName(name).orElse(null);
    }

    @Override
    @Transactional
    public Genre create(String name) {
        return genreRepository.save(new Genre(0, name));
    }
}
