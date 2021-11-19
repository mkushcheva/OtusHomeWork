package ru.diasoft.library.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.diasoft.library.domain.Author;
import ru.diasoft.library.domain.Book;
import ru.diasoft.library.domain.Comment;
import ru.diasoft.library.domain.Genre;
import ru.diasoft.library.utils.MessageSourceUtils;

import java.util.List;

import static java.lang.System.lineSeparator;

@Service
@RequiredArgsConstructor
public class WriterServiceImpl implements WriterService{
    private final MessageSourceUtils messageSource;

    @Override
    public void printMessage(String msg) {
        System.out.println(messageSource.getMessage(msg));
    }

    @Override
    public void printMessage(String msg, Object[] args) {
        System.out.println(messageSource.getMessage(msg, args));
    }

    @Override
    public void printAllAuthors(List<Author> authors) {
        System.out.println(messageSource.getMessage("author.listofauthors") + lineSeparator());

        for (Author author : authors) {
            System.out.println("№: " + author.getId() + " " + author.getName());
        }
    }

    @Override
    public void printAllGenres(List<Genre> genres) {
        System.out.println(messageSource.getMessage("genre.listofgenres") + lineSeparator());

        for (Genre genre : genres) {
            System.out.println("№: " + genre.getId() + " " + genre.getName());
        }
    }

    @Override
    public void printInfoBook(Book book) {
        System.out.println("№: " + book.getId() + lineSeparator()
                + messageSource.getMessage("book.message.title") + book.getTitle() + lineSeparator()
                + messageSource.getMessage("book.message.author") + book.getAuthor().getName() + lineSeparator()
                + messageSource.getMessage("book.message.ganre") + book.getGenre().getName() + lineSeparator()
                + messageSource.getMessage("book.message.comments") + lineSeparator()
        );
        for (Comment comment : book.getComments()) {
            System.out.println("№: " + comment.getId() + comment.getCommentText() + lineSeparator());
        }
    }

    @Override
    public void printAllBooks(List<Book> books) {
        System.out.println(messageSource.getMessage("book.listofbooks") + lineSeparator());

        for (Book book : books) {
            System.out.println("№: " + book.getId() + lineSeparator()
                    + messageSource.getMessage("book.message.title") + book.getTitle() + lineSeparator()
                    + messageSource.getMessage("book.message.author") + book.getAuthor().getName() + lineSeparator()
                    + messageSource.getMessage("book.message.ganre") + book.getGenre().getName() + lineSeparator()
                    + messageSource.getMessage("book.message.comments") + lineSeparator());

            for (Comment comment : book.getComments()) {
                System.out.println("№: " + comment.getId() + comment.getCommentText() + lineSeparator());
            }
        }
    }
}
