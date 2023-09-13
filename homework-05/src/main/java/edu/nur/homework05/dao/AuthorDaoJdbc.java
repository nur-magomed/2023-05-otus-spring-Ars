package edu.nur.homework05.dao;

import edu.nur.homework05.model.Author;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
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

    private final InsertAuthor insertAuthor;

    public AuthorDaoJdbc(JdbcOperations jdbc, NamedParameterJdbcOperations namedParamJdbcOps, DataSource dataSource) {
        this.jdbc = jdbc;
        this.namedParamJdbcOps = namedParamJdbcOps;
        this.insertAuthor = new InsertAuthor(dataSource);
    }

    @Override
    public Author save(Author author) {
        Map<String, Object> paramMap = Map.of("first_name", author.getFirstName(),
                "last_name", author.getLastName(),"birth_date", author.getBirthDate(),
                "created_date", author.getCreatedDate(), "modified_date", author.getModifiedDate());

        KeyHolder keyHolder = new GeneratedKeyHolder();
        insertAuthor.updateByNamedParam(paramMap, keyHolder);
        author.setId(Objects.requireNonNull(keyHolder.getKey()).longValue());
        return author;
    }

    @Override
    public Author update(Author author) {
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
