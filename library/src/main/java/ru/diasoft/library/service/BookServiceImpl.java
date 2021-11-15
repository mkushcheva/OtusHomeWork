package ru.diasoft.library.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.diasoft.library.dao.BookDao;
import ru.diasoft.library.domain.Book;
import ru.diasoft.library.utils.MessageSourceUtils;

import java.util.Optional;

import static java.lang.System.lineSeparator;

@RequiredArgsConstructor
@Service
public class BookServiceImpl implements BookService {
    private final BookDao bookDao;
    private final AuthorService authorService;
    private final GenreService genreService;

    private final MessageSourceUtils messageSource;

    @Override
    public Book create(String title, String authorName, String genreName) {
        Book book = bookDao.create(
                title,
                authorService.getByName(authorName),
                genreService.getByName(genreName)
        );
        System.out.println(messageSource.getMessage("book.create.successful", new Object[]{book}));
        return book;
    }

    @Override
    public void update(Long id, String title, String authorName, String genreName) {
        if (bookDao.getById(id).isPresent()) {
            Book book = new Book(
                    id,
                    title,
                    authorService.getByName(authorName),
                    genreService.getByName(genreName)
            );
            bookDao.update(book);
            System.out.println(messageSource.getMessage("book.update.successful"));
        } else {
            System.out.println(messageSource.getMessage("book.notFound"));
        }
    }

    @Override
    public void deleteByTitle(String title) {
        Optional<Book> book = bookDao.getByTitle(title);

        if (book.isPresent()) {
            bookDao.deleteById(book.get().getId());
            System.out.println(messageSource.getMessage("book.delete.successful", new Object[]{title}));
        } else {
            System.out.println(messageSource.getMessage("book.notFound"));
        }
    }

    @Override
    public void printInfoBook(String title) {
        Optional<Book> book = bookDao.getByTitle(title);

        if (book.isPresent()) {
            System.out.println("№: " + book.get().getId() + lineSeparator()
                    + messageSource.getMessage("book.message.title") + book.get().getTitle()
                    + messageSource.getMessage("book.message.author") + book.get().getAuthor().getName()
                    + messageSource.getMessage("book.message.ganre") + book.get().getGenre().getName());
        } else {
            System.out.println(messageSource.getMessage("book.notFound"));
        }
    }

    @Override
    public void printAllBooks() {
        System.out.println(messageSource.getMessage("book.listofbooks") + lineSeparator());

        for (Book book : bookDao.getAll()) {
            System.out.println("№: " + book.getId() + lineSeparator()
                    + messageSource.getMessage("book.message.title") + book.getTitle()
                    + messageSource.getMessage("book.message.author") + book.getAuthor().getName()
                    + messageSource.getMessage("book.message.ganre") + book.getGenre().getName());
        }
    }
}
