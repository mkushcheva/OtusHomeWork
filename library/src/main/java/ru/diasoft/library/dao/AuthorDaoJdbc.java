package ru.diasoft.library.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import ru.diasoft.library.domain.Author;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class AuthorDaoJdbc implements AuthorDao {
    private final NamedParameterJdbcOperations namedParameterJdbcOperations;

    @Override
    public Author create(String name) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("name", name);
        KeyHolder kh = new GeneratedKeyHolder();
        namedParameterJdbcOperations.update("insert into author (`author_name`) values (:name)", params, kh);
        return new Author((Long) kh.getKey(), name);
    }

    @Override
    public void deleteById(long id) {
        namedParameterJdbcOperations.update("delete from author where author_id = :id", Collections.singletonMap("id", id));
    }

    @Override
    public void update(Author author) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("name", author.getName());
        params.addValue("id", author.getId());

        namedParameterJdbcOperations.update(
                "update author set author_name = :name where author_id = :id", params);
    }

    @Override
    public Optional<Author> getById(long id) {
        return namedParameterJdbcOperations.query(
                "select * from author where author_id = :id", Collections.singletonMap("id", id), new AuthorMapper())
                .stream().findFirst();

    }

    @Override
    public Optional<Author> getByName(String name) {
        return namedParameterJdbcOperations.query(
                "select * from author where author_name = :name", Collections.singletonMap("name", name), new AuthorMapper())
                .stream().findFirst();
    }

    @Override
    public List<Author> getAll() {
        return namedParameterJdbcOperations.query("select * from author", new AuthorMapper());
    }

    private static class AuthorMapper implements RowMapper<Author> {

        @Override
        public Author mapRow(ResultSet resultSet, int i) throws SQLException {
            return new Author(resultSet.getLong("author_id"), resultSet.getString("author_name"));
        }
    }
}
