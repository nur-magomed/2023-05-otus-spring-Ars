package edu.nur.homework05.dao;

import edu.nur.homework05.model.Genre;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Repository
public class GenreDaoJdbc implements GenreDao {

    private final JdbcOperations jdbc;

    private final NamedParameterJdbcOperations namedParamJdbcOps;

    public GenreDaoJdbc(JdbcOperations jdbc, NamedParameterJdbcOperations namedParamJdbcOps) {
        this.jdbc = jdbc;
        this.namedParamJdbcOps = namedParamJdbcOps;
    }

    @Override
    public void save(Genre genre) {
        namedParamJdbcOps.update(
                "insert into t_genre (id, title,  created_date, modified_date) " +
                        "values (:id, :title, :created_date, :modified_date)",
                Map.of("id", genre.getId(),"title", genre.getTitle(),
                        "created_date", genre.getCreatedDate(), "modified_date", genre.getModifiedDate())
        );
    }

    @Override
    public Genre getById(long id) {
        Map<String, Object> params = Collections.singletonMap("id", id);
        return namedParamJdbcOps.queryForObject(
                "select id, title created_date, modified_date " +
                        "from t_genre where id = :id",
                params,
                new GenreMapper()
        );
    }

    @Override
    public List<Genre> getAll() {
        return jdbc.query(
                "select id, title created_date, modified_date from t_genre",
                new GenreMapper()
        );
    }

    @Override
    public void deleteById(long id) {
        Map<String, Object> params = Collections.singletonMap("id", id);
        namedParamJdbcOps.update(
                "delete from t_genre where id = :id", params
        );
    }

    @Override
    public int countAll() {
        Integer count = jdbc.queryForObject("select count(*) from t_genre", Integer.class);
        return count == null ? 0 : count;
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
