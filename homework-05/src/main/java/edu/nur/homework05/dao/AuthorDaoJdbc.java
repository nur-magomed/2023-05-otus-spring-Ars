package edu.nur.homework05.dao;

import edu.nur.homework05.model.Author;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Repository
public class AuthorDaoJdbc implements AuthorDao {

    private final JdbcOperations jdbc;

    private final NamedParameterJdbcOperations namedParamJdbcOps;

    public AuthorDaoJdbc(JdbcOperations jdbc, NamedParameterJdbcOperations namedParamJdbcOps) {
        this.jdbc = jdbc;
        this.namedParamJdbcOps = namedParamJdbcOps;
    }

    @Override
    public Author save(Author author) {
        if (author.getId() == 0) {
            return insert(author);
        } else {
            return update(author);
        }
    }

    private Author insert(Author author) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("first_name", author.getFirstName());
        params.addValue("last_name", author.getLastName());
        params.addValue("birth_date", author.getBirthDate());
        params.addValue("created_date", author.getCreatedDate());
        params.addValue("modified_date", author.getModifiedDate());

        KeyHolder keyHolder = new GeneratedKeyHolder();
        namedParamJdbcOps.update(
                "INSERT INTO t_author(first_name, last_name, birth_date, created_date, modified_date) " +
                        "VALUES (:first_name, :last_name, :birth_date, :created_date, :modified_date)",
                params, keyHolder);
        author.setId(Objects.requireNonNull(keyHolder.getKey()).longValue());
        return author;
    }

    private Author update(Author author) {
        Date now = new Date();
        namedParamJdbcOps.update(
                "UPDATE t_author SET first_name=:first_name, last_name=:last_name, modified_date=:modified_date " +
                        "WHERE id=:id",
                Map.of("id", author.getId(),"first_name", author.getFirstName(),
                        "last_name", author.getLastName(), "modified_date", now)
        );
        author.setModifiedDate(now);
        return author;
    }

    @Override
    public Author getById(long id) {
        Map<String, Object> params = Collections.singletonMap("id", id);
        return namedParamJdbcOps.queryForObject(
                "SELECT id, first_name, last_name, birth_date, created_date, modified_date " +
                        "FROM t_author WHERE id =:id",
                params,
                new AuthorMapper()
        );
    }

    @Override
    public List<Author> getAll() {
        return jdbc.query(
                "SELECT id, first_name, last_name, birth_date, created_date, modified_date FROM t_author",
                new AuthorMapper()
        );
    }

    @Override
    public void deleteById(long id) {
        Map<String, Object> params = Collections.singletonMap("id", id);
        namedParamJdbcOps.update("DELETE FROM t_author WHERE id=:id", params);
    }

    @Override
    public int countAll() {
        Integer count = jdbc.queryForObject("SELECT count(*) FROM t_author", Integer.class);
        return count == null ? 0 : count;
    }

    @Override
    public List<Author> getAllUsed() {
         return jdbc.query("SELECT a.id as id, a.first_name as first_name, a.last_name as last_name, " +
                        "a.birth_date as birth_date, a.created_date as created_date, a.modified_date as modified_date " +
                        "FROM t_author a INNER JOIN t_book_author ba ON a.id = ba.author_id " +
                        "GROUP BY a.id, a.first_name ORDER BY a.first_name", new AuthorMapper());
    }

    private static class AuthorMapper implements RowMapper<Author> {
        @Override
        public Author mapRow(ResultSet resultSet, int i) throws SQLException {
            long id = resultSet.getLong("id");
            String firstName = resultSet.getString("first_name");
            String lastName = resultSet.getString("last_name");
            LocalDate birthDateLocalDate = resultSet.getObject("birth_date", LocalDate.class);
            Date birthDate = Date.from(birthDateLocalDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
            Date createdDate = resultSet.getDate("created_date");
            Date modifiedDate = resultSet.getDate("modified_date");
            return new Author(id, firstName, lastName, birthDate, createdDate, modifiedDate);
        }
    }
}
