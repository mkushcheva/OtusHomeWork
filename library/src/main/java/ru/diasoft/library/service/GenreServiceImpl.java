package ru.diasoft.library.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.diasoft.library.dao.GenreDao;
import ru.diasoft.library.domain.Genre;
import ru.diasoft.library.utils.MessageSourceUtils;

import java.util.Optional;

import static java.lang.System.lineSeparator;

@RequiredArgsConstructor
@Service
public class GenreServiceImpl implements GenreService {
    private final GenreDao genreDao;
    private final MessageSourceUtils messageSource;

    @Override
    public Genre getByName(String name) {
        return genreDao.getByName(name).orElseGet(() -> genreDao.create(name));
    }

    @Override
    public void create(String name) {
        Genre genre = genreDao.create(name);
        System.out.println(messageSource.getMessage("genre.create.successful", new Object[]{genre}));
    }

    @Override
    public void printAllGenres() {
        System.out.println(messageSource.getMessage("genre.listofgenres") + lineSeparator());

        for (Genre genre : genreDao.getAll()) {
            System.out.println("№: " + genre.getId() + " " + genre.getName());
        }
    }

    @Override
    public void deleteByName(String name) {
        Optional<Genre> genre = genreDao.getByName(name);

        if (genre.isPresent()) {
            genreDao.deleteById(genre.get().getId());
            System.out.println(messageSource.getMessage("genre.delete.successful", new Object[]{name}));
        } else {
            System.out.println(messageSource.getMessage("genre.notFound"));
        }
    }
}
