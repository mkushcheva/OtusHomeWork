package ru.diasoft.library.repository;

import ru.diasoft.library.domain.Comment;

import java.util.List;
import java.util.Optional;

public interface CommentRepository {
    Comment create(Comment comment);

    void deleteById(long id);

    Optional<Comment> getById(long id);

    List<Comment> getAll();
}
