package ru.diasoft.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.diasoft.library.domain.Author;

import java.util.Optional;

public interface AuthorRepositoryJpa extends JpaRepository<Author, Long> {
    Optional<Author> findByName(String name);
}
