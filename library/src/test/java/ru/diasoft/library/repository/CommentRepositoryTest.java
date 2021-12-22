package ru.diasoft.library.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import ru.diasoft.library.domain.Comment;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DisplayName("Репозиторий для работы с комментариями должен")
@DataJpaTest
class CommentRepositoryTest {
    private static final String COMMENT_TEXT_NEW = "Новый комментарий";
    private static final long EXISTING_COMMENT_ID = 1;

    @Autowired
    private CommentRepository commentRepository;

    @DisplayName("находить все комментарии ")
    @Test
    void shouldFindAllComment() {
        List<Comment> commentList = commentRepository.findAll();
        assertThat(commentList.size()).isEqualTo(4);
    }

    @Test
    @DisplayName("добавлять комментарий в БД к существующей книге")
    void shouldCreateCommentTest() {
        int countBefore = commentRepository.findAll().size();
        Comment newComment = new Comment(0L, COMMENT_TEXT_NEW);
        Comment createComment = commentRepository.save(newComment);

        int countAfter = commentRepository.findAll().size();

        assertThat(createComment.getCommentText()).isEqualTo(COMMENT_TEXT_NEW);
        assertThat(countAfter).isGreaterThan(countBefore);
    }

    @Test
    @DisplayName("удалять комментарий по идентификатору")
    void shouldDeleteCommentById() {
        assertThatCode(() -> commentRepository.findById(EXISTING_COMMENT_ID)).doesNotThrowAnyException();
        int countBefore = commentRepository.findAll().size();
        commentRepository.deleteById(EXISTING_COMMENT_ID);
        int countAfter = commentRepository.findAll().size();

        assertThat(countAfter).isLessThan(countBefore);
    }

    @Test
    @DisplayName("Найти комментарий по id")
    void shouldCommentById() {
        Comment actualComment = commentRepository.findById(EXISTING_COMMENT_ID).orElse(null);
        assertNotNull(actualComment);
        assertThat(actualComment.getId()).isEqualTo(EXISTING_COMMENT_ID);
    }
}