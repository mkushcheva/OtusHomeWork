package ru.diasoft.library.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.diasoft.library.domain.Book;

import java.util.List;
import java.util.Optional;

public interface BookRepositoryJpa extends JpaRepository<Book, Long> {
    @EntityGraph(attributePaths = {"author", "genre"})
    Optional<Book> findByTitle(String title);

    @EntityGraph(attributePaths = {"author", "genre"})
    List<Book> findAll();
}
