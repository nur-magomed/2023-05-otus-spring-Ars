package edu.nur.homework05.dao;

import edu.nur.homework05.model.Genre;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Repository
public class GenreDaoJdbc implements GenreDao {

    private final JdbcOperations jdbc;

    private final NamedParameterJdbcOperations namedParamJdbcOps;

    private final InsertGenre insertGenre;

    public GenreDaoJdbc(JdbcOperations jdbc, NamedParameterJdbcOperations namedParamJdbcOps, DataSource dataSource) {
        this.jdbc = jdbc;
        this.namedParamJdbcOps = namedParamJdbcOps;
        this.insertGenre = new InsertGenre(dataSource);
    }

    @Override
    public void insert(Genre genre) {
        Map<String, Object> paramMap = Map.of("title", genre.getTitle(),
                "created_date", genre.getCreatedDate(), "modified_date", genre.getModifiedDate());

        KeyHolder keyHolder = new GeneratedKeyHolder();
        insertGenre.updateByNamedParam(paramMap, keyHolder);
        genre.setId(Objects.requireNonNull(keyHolder.getKey()).longValue());
    }

    @Override
    public void update(Genre genre) {
        namedParamJdbcOps.update(
                "UPDATE t_genre SET title=:title, modified_date=:modified_date WHERE  id=:id",
                Map.of("id", genre.getId(),"title", genre.getTitle(),"modified_date", new Date())
        );
    }

    @Override
    public Genre getById(long id) {
        Map<String, Object> params = Collections.singletonMap("id", id);
        return namedParamJdbcOps.queryForObject(
                "SELECT id, title, created_date, modified_date FROM t_genre WHERE id =:id",
                params, new GenreMapper()
        );
    }

    @Override
    public List<Genre> getAll() {
        return jdbc.query("SELECT id, title, created_date, modified_date FROM t_genre", new GenreMapper());
    }

    @Override
    public void deleteById(long id) {
        Map<String, Object> params = Collections.singletonMap("id", id);
        namedParamJdbcOps.update("DELETE FROM t_genre WHERE id = :id", params);
    }

    @Override
    public int countAll() {
        Integer count = jdbc.queryForObject("SELECT count(*) FROM t_genre", Integer.class);
        return count == null ? 0 : count;
    }

    @Override
    public int getMaxId() {
        return jdbc.queryForObject("SELECT max(id) FROM t_genre", Integer.class);
    }

    private static class GenreMapper implements RowMapper<Genre> {
        @Override
        public Genre mapRow(ResultSet resultSet, int i) throws SQLException {
            long id = resultSet.getLong("id");
            String title = resultSet.getString("title");
            Date createdDate = resultSet.getDate("created_date");
            Date modifiedDate = resultSet.getDate("modified_date");
            return new Genre(id, title, createdDate, modifiedDate);
        }
    }

}
