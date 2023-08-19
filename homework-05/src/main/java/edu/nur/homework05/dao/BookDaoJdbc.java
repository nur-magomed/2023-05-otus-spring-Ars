package edu.nur.homework05.dao;

import edu.nur.homework05.model.Author;
import edu.nur.homework05.model.Book;
import edu.nur.homework05.model.Genre;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

@Repository
public class BookDaoJdbc implements BookDao{

    private final JdbcOperations jdbc;

    private final NamedParameterJdbcOperations namedParamJdbcOps;

    public BookDaoJdbc(JdbcOperations jdbc, NamedParameterJdbcOperations namedParamJdbcOps) {
        this.jdbc = jdbc;
        this.namedParamJdbcOps = namedParamJdbcOps;
    }

    @Override
    public void save(Book book) {

        namedParamJdbcOps.update(
                "insert into t_book(id, title,  created_date, modified_date) values (:id, :title, :created_date, :modified_date)",
                Map.of("id", book.getId(), "title", book.getTitle(), "created_date", book.getCreatedDate(), "modified_date", new Date())
        );

        for (Author author: book.getAuthors()) {
            namedParamJdbcOps.update(
                    "insert into t_book_author(id, book_id, author_id,  created_date, modified_date) values (:id, :title, :created_date, :modified_date)",
                    Map.of("id", book.getId(), "book_id", book.getId(), "author_id", author.getId(), "created_date", book.getCreatedDate(), "modified_date", new Date())
            );
        }

        for (Genre genre: book.getGenres()) {
            namedParamJdbcOps.update(
                    "insert into t_book_author(id, book_id, genre_id,  created_date, modified_date) values (:id, :title, :created_date, :modified_date)",
                    Map.of("id", book.getId(), "book_id", book.getId(), "genre_id", genre.getId(), "created_date", book.getCreatedDate(), "modified_date", new Date())
            );
        }
    }

    @Override
    public Book getById(long id) {
        Map<String, Object> params = Collections.singletonMap("id", id);
        List<Book> books = namedParamJdbcOps.query(
                "SELECT b.id AS b_id, b.title AS b_title, b.created_date AS b_created_date, b.modified_date AS b_modified_date, " +
                    "a.id AS a_id, a.first_name AS a_first_name, a.last_name AS a_last_name, a.birth_date as a_birth_date, a.created_date AS a_created_date, a.modified_date AS a_modified_date, " +
                    "g.id AS g_id, g.title AS g_title, g.created_date AS g_created_date, g.modified_date AS g_modified_date " +
                    "FROM t_book b " +
                    "LEFT JOIN t_book_author ba ON b.id = ba.BOOK_ID " +
                    "LEFT JOIN t_author a ON ba.author_id = a.id " +
                    "LEFT JOIN t_book_genre bg ON b.id = bg.book_id " +
                    "LEFT JOIN t_genre g ON bg.genre_id = g.id " +
                    "WHERE b.id = :id ",
                params,
                new BookExtractor()
        );
        return books.get(0);
    }

    @Override
    public List<Book> getAll() {
        return jdbc.query(
                "SELECT b.id AS b_id, b.title AS b_title, b.created_date AS b_created_date, b.modified_date AS b_modified_date, " +
                    "a.id AS a_id, a.first_name AS a_first_name, a.last_name AS a_last_name, a.birth_date as a_birth_date, a.created_date AS a_created_date, a.modified_date AS a_modified_date, " +
                    "g.id AS g_id, g.title AS g_title, g.created_date AS g_created_date, g.modified_date AS g_modified_date " +
                    "FROM t_book b " +
                    "LEFT JOIN t_book_author ba ON b.id = ba.BOOK_ID " +
                    "LEFT JOIN t_author a ON ba.author_id = a.id " +
                    "LEFT JOIN t_book_genre bg ON b.id = bg.book_id " +
                    "LEFT JOIN t_genre g ON bg.genre_id = g.id " ,
                new BookExtractor()
        );
    }

    @Override
    public void deleteById(long id) {
        Map<String, Object> params = Collections.singletonMap("id", id);
        namedParamJdbcOps.update(
                "delete from t_book where id = :id", params
        );
    }


    private static class BookExtractor implements ResultSetExtractor<List<Book>> {

        @Override
        public List<Book> extractData(ResultSet resultSet) throws SQLException, DataAccessException {
            Map<Long, Book> bookMap = new HashMap<>();

            while(resultSet.next()) {
                long bookId = resultSet.getLong("b_id");
                String bookTitle = resultSet.getString("b_title");
                Date bookCreatedDate = resultSet.getDate("b_created_date");
                Date bookModifiedDate = resultSet.getDate("b_modified_date");
                if (!bookMap.containsKey(bookId)) {
                    bookMap.put(bookId, new Book(bookId, bookTitle, new ArrayList<>(), new ArrayList<>(), bookCreatedDate, bookModifiedDate));
                }
                Book book = bookMap.get(bookId);

                long authorId = resultSet.getLong("a_id");
                String firstName = resultSet.getString("a_first_name");
                String lastName = resultSet.getString("a_last_name");
                Date birthDate = resultSet.getDate("a_birth_date");
                Date authorCreatedDate = resultSet.getDate("a_created_date");
                Date authorModifiedDate = resultSet.getDate("a_modified_date");
                Author author = new Author(authorId, firstName, lastName, birthDate, authorCreatedDate, authorModifiedDate);
                if (!book.getAuthors().contains(author)) {
                    book.getAuthors().add(author);
                }

                long genreId = resultSet.getLong("g_id");
                String genreTitle = resultSet.getString("g_title");
                Date genreCreatedDate = resultSet.getDate("g_created_date");
                Date genreModifiedDate = resultSet.getDate("g_modified_date");
                Genre genre = new Genre(genreId, genreTitle, genreCreatedDate, genreModifiedDate);
                if (!book.getGenres().contains(genre)) {
                    book.getGenres().add(genre);
                }
            }
            return bookMap.values().stream().toList();
        }
    }
}
