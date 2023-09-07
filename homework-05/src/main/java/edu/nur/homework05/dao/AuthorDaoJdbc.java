package edu.nur.homework05.dao;

import edu.nur.homework05.model.Author;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Repository
public class AuthorDaoJdbc implements AuthorDao {

    private final JdbcOperations jdbc;

    private final NamedParameterJdbcOperations namedParamJdbcOps;

    public AuthorDaoJdbc(JdbcOperations jdbc, NamedParameterJdbcOperations namedParamJdbcOps) {
        this.jdbc = jdbc;
        this.namedParamJdbcOps = namedParamJdbcOps;
    }

    @Override
    public void insert(Author author) {
        namedParamJdbcOps.update(
                "INSERT INTO t_author(id, first_name, last_name, birth_date, created_date, modified_date) " +
                        "VALUES (:id, :first_name, :last_name, :birth_date, :created_date, :modified_date)",
                Map.of("id", author.getId(),"first_name", author.getFirstName(), "last_name", author.getLastName(),
                        "birth_date", author.getBirthDate(), "created_date", author.getCreatedDate(),
                        "modified_date", author.getModifiedDate())
        );
    }

    @Override
    public void update(Author author) {
        namedParamJdbcOps.update(
                "UPDATE t_author SET first_name=:first_name, last_name=:last_name, modified_date=:modified_date " +
                        "WHERE id=:id",
                Map.of("id", author.getId(),"first_name", author.getFirstName(), "last_name", author.getLastName(),
                        "modified_date", new Date())
        );
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
    public int getMaxId() {
        return jdbc.queryForObject("SELECT max(id) FROM t_author", Integer.class);
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
