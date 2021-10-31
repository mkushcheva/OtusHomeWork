package ru.diasoft.library.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import ru.diasoft.library.domain.Genre;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class GenreDaoJdbc implements GenreDao {
    private final NamedParameterJdbcOperations namedParameterJdbcOperations;

    @Override
    public Genre create(String name) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("name", name);
        KeyHolder kh = new GeneratedKeyHolder();
        namedParameterJdbcOperations.update("insert into genre (`genre_name`) values (:name)", params, kh);
        return new Genre((Long) kh.getKey(), name);
    }

    @Override
    public void deleteById(long id) {
        namedParameterJdbcOperations.update("delete from genre where genre_id = :id", Collections.singletonMap("id", id));
    }

    @Override
    public void update(Genre genre) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("name", genre.getName());
        params.addValue("id", genre.getId());

        namedParameterJdbcOperations.update(
                "update genre set genre_name = :name where genre_id = :id", params);
    }

    @Override
    public Optional<Genre> getById(long id) {
        return namedParameterJdbcOperations.query(
                "select * from genre where genre_id = :id", Collections.singletonMap("id", id), new GenreMapper())
                .stream().findFirst();
    }

    @Override
    public Optional<Genre> getByName(String name) {
        return namedParameterJdbcOperations.query(
                "select * from genre where genre_name = :name", Collections.singletonMap("name", name), new GenreMapper())
                .stream().findFirst();
    }

    @Override
    public List<Genre> getAll() {
        return namedParameterJdbcOperations.query("select * from genre", new GenreMapper());
    }

    private static class GenreMapper implements RowMapper<Genre> {

        @Override
        public Genre mapRow(ResultSet resultSet, int i) throws SQLException {
            return new Genre(resultSet.getLong("genre_id"), resultSet.getString("genre_name"));
        }
    }
}
