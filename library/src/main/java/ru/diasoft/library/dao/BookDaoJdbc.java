package ru.diasoft.library.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import ru.diasoft.library.domain.Author;
import ru.diasoft.library.domain.Book;
import ru.diasoft.library.domain.Genre;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class BookDaoJdbc implements BookDao {
    private final NamedParameterJdbcOperations namedParameterJdbcOperations;

    @Override
    public Book create(String title, Author author, Genre genre) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("title", title);
        params.addValue("author_id", author.getId());
        params.addValue("genre_id", genre.getId());

        KeyHolder kh = new GeneratedKeyHolder();
        namedParameterJdbcOperations.update("insert into book (book_title, author_id, genre_id) values (:title, :author_id, :genre_id)", params, kh);
        return new Book((Long) kh.getKey(), title, author, genre);
    }

    @Override
    public void update(Book book) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", book.getId());
        params.addValue("title", book.getTitle());
        params.addValue("author_id", book.getAuthor().getId());
        params.addValue("genre_id", book.getGenre().getId());

        namedParameterJdbcOperations.update("update book " +
                "set book_title = :title, " +
                "author_id = :author_id, " +
                "genre_id = :genre_id " +
                "where book_id = :id", params);
    }

    @Override
    public Optional<Book> getById(long id) {
        Map<String, Object> params = Collections.singletonMap("id", id);
        return namedParameterJdbcOperations.query(
                "select b.book_id, " +
                        "b.book_title, " +
                        "b.author_id, " +
                        "a.author_name as author_name, " +
                        "b.genre_id, " +
                        "g.genre_name as genre_name " +
                        "from book b" +
                        " inner join author a on a.author_id = b.author_id" +
                        " inner join genre g on g.genre_id = b.genre_id where b.book_id = :id", params, new BookDaoJdbc.BookMapper())
                .stream().findFirst();
    }

    @Override
    public Optional<Book> getByTitle(String title) {
        Map<String, Object> params = Collections.singletonMap("title", title);
        return namedParameterJdbcOperations.query(
                "select b.book_id, " +
                        "b.book_title, " +
                        "b.author_id, " +
                        "a.author_name as author_name, " +
                        "b.genre_id, " +
                        "g.genre_name as genre_name " +
                        "from book b" +
                        " inner join author a on a.author_id = b.author_id" +
                        " inner join genre g on g.genre_id = b.genre_id where b.book_title = :title", params, new BookDaoJdbc.BookMapper())
                .stream().findFirst();
    }

    @Override
    public List<Book> getAll() {
        return namedParameterJdbcOperations.query(
                "select b.book_id, " +
                        "b.book_title, " +
                        "b.author_id, " +
                        "a.author_name as author_name, " +
                        "b.genre_id, " +
                        "g.genre_name as genre_name " +
                        "from book b" +
                        " inner join author a on a.author_id = b.author_id" +
                        " inner join genre g on g.genre_id = b.genre_id", new BookDaoJdbc.BookMapper());
    }

    @Override
    public void deleteById(long id) {
        Map<String, Object> params = Collections.singletonMap("id", id);
        namedParameterJdbcOperations.update("delete from book where book_id = :id", params);
    }


    private static class BookMapper implements RowMapper<Book> {

        @Override
        public Book mapRow(ResultSet resultSet, int i) throws SQLException {
            long id = resultSet.getLong("book_id");
            String title = resultSet.getString("book_title");
            Author author = new Author(resultSet.getLong("author_id"), resultSet.getString("author_name"));
            Genre genre = new Genre(resultSet.getLong("genre_id"), resultSet.getString("genre_name"));
            return new Book(id, title, author, genre);
        }
    }
}
