package ru.diasoft.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.diasoft.library.domain.Comment;

public interface CommentRepositoryJpa extends JpaRepository<Comment, Long> {
}
