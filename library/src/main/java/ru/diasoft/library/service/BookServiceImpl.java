package ru.diasoft.library.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.diasoft.library.domain.Author;
import ru.diasoft.library.domain.Book;
import ru.diasoft.library.domain.Comment;
import ru.diasoft.library.domain.Genre;
import ru.diasoft.library.repository.BookRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final AuthorService authorService;
    private final GenreService genreService;

    private final WriterService writerService;

    @Override
    @Transactional
    public Book create(String title, String authorName, String genreName) {
        //Изначально книга добавляется с пустым комментарием. комментировать книгу можно после ее прочтения.
        List<Comment> comments = new ArrayList<>();

        Book book = bookRepository.create(
                new Book(
                        0,
                        title,
                        authorService.getByName(authorName),
                        genreService.getByName(genreName),
                        comments)
        );
        writerService.printMessage("book.create.successful", new Object[]{book});
        return book;
    }

    @Override
    @Transactional
    public void update(Long id, String title, String authorName, String genreName) {
        //У книги нельзя обновить комментарий
        Optional<Book> book = bookRepository.getById(id);

        if (book.isPresent()) {
            Book bookUpdate = book.get();
            bookUpdate.setAuthor(getAuthor(authorName));
            bookUpdate.setGenre(getGenre(genreName));
            bookUpdate.setTitle(title);

            bookRepository.update(bookUpdate);
            writerService.printMessage("book.update.successful");
        } else {
            writerService.printMessage("book.notFound");
        }
    }

    @Override
    @Transactional
    public void deleteByTitle(String title) {
        Optional<Book> book = bookRepository.getByTitle(title);

        if (book.isPresent()) {
            bookRepository.deleteById(book.get().getId());
            writerService.printMessage("book.delete.successful", new Object[]{title});
        } else {
            writerService.printMessage("book.notFound");
        }
    }

    @Override
    @Transactional(readOnly = true)
    public void printInfoBook(String title) {
        Optional<Book> book = bookRepository.getByTitle(title);

        if (book.isPresent()) {
            writerService.printInfoBook(book.get());
        } else {
            writerService.printMessage("book.notFound");
        }
    }

    @Override
    @Transactional(readOnly = true)
    public void printAllBooks() {
        writerService.printAllBooks(bookRepository.getAll());
    }

    @Override
    @Transactional
    public void addCommentToBook(String title, String commentText) {
        Optional<Book> book = bookRepository.getByTitle(title);

        if (book.isPresent()) {
            Book commentedBook = book.get();
            commentedBook.getComments().add(new Comment(0, commentText));
            bookRepository.create(commentedBook);
        } else {
            writerService.printMessage("book.notFound");
        }
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
