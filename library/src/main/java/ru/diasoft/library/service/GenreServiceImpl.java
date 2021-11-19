package ru.diasoft.library.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.diasoft.library.domain.Genre;
import ru.diasoft.library.repository.GenreRepository;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class GenreServiceImpl implements GenreService {
    private final GenreRepository genreRepository;
    private final WriterService writerService;

    @Override
    public Genre getByName(String name) {
        return genreRepository.getByName(name).orElse(null);
    }

    @Override
    @Transactional
    public Genre create(String name) {
        Genre genre = genreRepository.create(new Genre(0, name));
        writerService.printMessage("genre.create.successful", new Object[]{genre});
        return genre;
    }

    @Override
    @Transactional(readOnly = true)
    public void printAllGenres() {
        writerService.printAllGenres(genreRepository.getAll());
    }

    @Override
    @Transactional
    public void deleteByName(String name) {
        Optional<Genre> genre = genreRepository.getByName(name);

        if (genre.isPresent()) {
            genreRepository.deleteById(genre.get().getId());
            writerService.printMessage("genre.delete.successful", new Object[]{name});
        } else {
            writerService.printMessage("genre.notFound");
        }
    }
}
