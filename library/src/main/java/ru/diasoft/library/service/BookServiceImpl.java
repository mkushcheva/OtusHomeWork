package ru.diasoft.library.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.diasoft.library.domain.Author;
import ru.diasoft.library.domain.Book;
import ru.diasoft.library.domain.Genre;
import ru.diasoft.library.repository.BookRepository;

import java.util.ArrayList;

@RequiredArgsConstructor
@Service
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final AuthorService authorService;
    private final GenreService genreService;

    @Override
    @Transactional
    public Book create(String title, String authorName, String genreName) {
        //Изначально книга добавляется с пустым комментарием. комментировать книгу можно после ее прочтения.
        return bookRepository.save(
                new Book(
                        0,
                        title,
                        getAuthor(authorName),
                        getGenre(genreName),
                        new ArrayList<>())
        );
    }

    private Author getAuthor(String authorName) {
        Author author = authorService.getByName(authorName);
        if (author != null) return author;
        return authorService.create(authorName);
    }

    private Genre getGenre(String genreName) {
        Genre genre = genreService.getByName(genreName);
        if (genre != null) return genre;
        return genreService.create(genreName);
    }
}
