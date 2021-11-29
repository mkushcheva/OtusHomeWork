package ru.diasoft.library.service;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

@ShellComponent
@RequiredArgsConstructor
public class ShellCommands {
    private final BookService bookService;
    private final AuthorService authorService;
    private final GenreService genreService;

    /*
         Команды для Книги
     */
    @ShellMethod(value = "Add Book", key = {"ab", "Add Book"})
    public void addBook(String title, String authorName, String genreName) {
        bookService.create(title, authorName, genreName);
    }

    @ShellMethod(value = "Delete Book", key = {"db", "Delete Book"})
    public void deleteBook(String title) {
        bookService.deleteByTitle(title);
    }

    @ShellMethod(value = "Update Book", key = {"ub", "Update Book"})
    public void updateBook(Long id, String title, String authorName, String genreName) {
        bookService.update(id, title, authorName, genreName);
    }

    @ShellMethod(value = "Find All Books", key = {"flb", "Find All Books"})
    public void findAllBooks() {
        bookService.printAllBooks();
    }

    @ShellMethod(value = "Find Book", key = {"fb", "Find Book"})
    public void findBook(String title) {
        bookService.printInfoBook(title);
    }

    @ShellMethod(value = "Add Comment", key = {"ac", "Add Comment"})
    public void addCommentToBook(String title, String commentTest) {
        bookService.addCommentToBook(title, commentTest);
    }

    /*
     Команды для Автора
    */

    @ShellMethod(value = "Add Author", key = {"aa", "Add Author"})
    public void addAuthor(String authorName) {
        authorService.create(authorName);
    }

    @ShellMethod(value = "Find All Authors", key = {"faa", "Find All Authors"})
    public void findAllAuthors() {
        authorService.printAllAuthors();
    }

    @ShellMethod(value = "Delete Author", key = {"da", "Delete Author"})
    public void deleteAuthor(String name) {
        authorService.deleteByName(name);
    }

      /*
     Команды для Жанра
    */

    @ShellMethod(value = "Add Genre", key = {"ag", "Add Genre"})
    public void addGenre(String genreName) {
        genreService.create(genreName);
    }

    @ShellMethod(value = "Find All Genres", key = {"fag", "Find All Genres"})
    public void findAllGenres() {
        genreService.printAllGenres();
    }

    @ShellMethod(value = "Delete Genre", key = {"dg", "Delete Genre"})
    public void deleteGenre(String name) {
        genreService.deleteByName(name);
    }

}
