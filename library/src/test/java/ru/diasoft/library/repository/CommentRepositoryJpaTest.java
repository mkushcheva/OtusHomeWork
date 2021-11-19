package ru.diasoft.library.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import ru.diasoft.library.domain.Comment;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DisplayName("Репозиторий для работы с комментариями должен")
@DataJpaTest
@Import(CommentRepositoryJpa.class)
class CommentRepositoryJpaTest {
    private static final String COMMENT_TEXT_NEW = "Новый комментарий";
    private static final long EXISTING_BOOK_ID = 1; //Волшебник изумрудного города
    private static final long EXISTING_COMMENT_ID = 1;

    @Autowired
    private CommentRepository commentRepository;

    @DisplayName("находить все комментарии ")
    @Test
    void shouldFindAllComment() {
        List<Comment> commentList = commentRepository.getAll();
        assertThat(commentList.size()).isEqualTo(4);
    }

    @Test
    @DisplayName("добавлять комментарий в БД к существующей книге")
    void shouldCreateCommentTest() {
        int countBefore = commentRepository.getAll().size();
        Comment newComment = new Comment(0L, COMMENT_TEXT_NEW);
        Comment createComment = commentRepository.create(newComment);

        int countAfter = commentRepository.getAll().size();

        assertThat(createComment.getCommentText()).isEqualTo(COMMENT_TEXT_NEW);
        assertThat(countAfter).isGreaterThan(countBefore);
    }

    @Test
    @DisplayName("удалять комментарий по идентификатору")
    void shouldDeleteCommentById() {
        assertThatCode(() -> commentRepository.getById(EXISTING_COMMENT_ID)).doesNotThrowAnyException();
        int countBefore = commentRepository.getAll().size();
        commentRepository.deleteById(EXISTING_COMMENT_ID);
        int countAfter = commentRepository.getAll().size();

        assertThat(countAfter).isLessThan(countBefore);
    }

    @Test
    @DisplayName("Найти комментарий по id")
    void shouldCommentById() {
        Comment actualComment = commentRepository.getById(EXISTING_COMMENT_ID).orElse(null);
        assertNotNull(actualComment);
        assertThat(actualComment.getId()).isEqualTo(EXISTING_COMMENT_ID);
    }
}