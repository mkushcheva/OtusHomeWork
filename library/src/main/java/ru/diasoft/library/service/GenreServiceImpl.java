package ru.diasoft.library.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.diasoft.library.domain.Genre;
import ru.diasoft.library.repository.GenreRepository;
import ru.diasoft.library.utils.MessageSourceUtils;

import java.util.Optional;

import static java.lang.System.lineSeparator;

@RequiredArgsConstructor
@Service
public class GenreServiceImpl implements GenreService {
    private final GenreRepository genreRepository;
    private final MessageSourceUtils messageSource;

    @Override
    public Genre getByName(String name) {
        return genreRepository.getByName(name).orElse(null);
    }

    @Override
    @Transactional
    public Genre create(String name) {
        Genre genre = genreRepository.create(new Genre(0, name));
        System.out.println(messageSource.getMessage("genre.create.successful", new Object[]{genre}));
        return  genre;
    }

    @Override
    @Transactional(readOnly = true)
    public void printAllGenres() {
        System.out.println(messageSource.getMessage("genre.listofgenres") + lineSeparator());

        for (Genre genre : genreRepository.getAll()) {
            System.out.println("№: " + genre.getId() + " " + genre.getName());
        }
    }

    @Override
    @Transactional
    public void deleteByName(String name) {
        Optional<Genre> genre = genreRepository.getByName(name);

        if (genre.isPresent()) {
            genreRepository.deleteById(genre.get().getId());
            System.out.println(messageSource.getMessage("genre.delete.successful", new Object[]{name}));
        } else {
            System.out.println(messageSource.getMessage("genre.notFound"));
        }
    }
}
