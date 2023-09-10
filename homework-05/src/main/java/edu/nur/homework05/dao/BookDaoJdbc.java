package edu.nur.homework05.dao;

import edu.nur.homework05.model.Author;
import edu.nur.homework05.model.Book;
import edu.nur.homework05.model.Genre;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.ResultSetExtractor;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Date;
import java.util.ArrayList;

@Repository
public class BookDaoJdbc implements BookDao {

    private final JdbcOperations jdbc;

    private final NamedParameterJdbcOperations namedParamJdbcOps;

    private final InsertBook insertBook;

    public BookDaoJdbc(JdbcOperations jdbc, NamedParameterJdbcOperations namedParamJdbcOps, DataSource dataSource) {
        this.jdbc = jdbc;
        this.namedParamJdbcOps = namedParamJdbcOps;
        this.insertBook = new InsertBook(dataSource);
    }

    @Override
    public void insert(Book book) {
        Map<String, Object> paramMap = Map.of("title", book.getTitle(), "created_date", book.getCreatedDate(),
                "modified_date", new Date());
        KeyHolder keyHolder = new GeneratedKeyHolder();
        insertBook.updateByNamedParam(paramMap, keyHolder);
        book.setId(keyHolder.getKey().longValue());
        System.out.println("book: " + book.getId());
        for (Author author: book.getAuthors()) {
            namedParamJdbcOps.update("INSERT INTO t_book_author(book_id, author_id, created_date, modified_date) " +
                            "VALUES (:book_id, :author_id,  :created_date, :modified_date)",
                    Map.of("book_id", book.getId(), "author_id", author.getId(),
                            "created_date", new Date(), "modified_date", new Date()));
        }
        for (Genre genre: book.getGenres()) {
            namedParamJdbcOps.update("INSERT INTO t_book_genre(book_id, genre_id, created_date, modified_date) " +
                            "VALUES (:book_id, :genre_id, :created_date, :modified_date)",
                    Map.of("book_id", book.getId(),"genre_id", genre.getId(),
                            "created_date", book.getCreatedDate(), "modified_date", new Date()));
        }
    }

    @Override
    public void update(Book book) {
        namedParamJdbcOps.update(
                "UPDATE t_book SET title=:title, modified_date=:modified_date WHERE  id=:id ",
                Map.of("id", book.getId(), "title", book.getTitle(), "modified_date", new Date()));

        updateBookAuthors(book);
        updateBookGenres(book);
    }

    @Override
    public Book getById(long id) {
        Map<String, Object> params = Collections.singletonMap("id", id);
        List<Book> books = namedParamJdbcOps.query(
                "SELECT b.id AS b_id, b.title AS b_title, b.created_date AS b_created_date, " +
                    "b.modified_date AS b_modified_date, a.id AS a_id, a.first_name AS a_first_name, " +
                    "a.last_name AS a_last_name, a.birth_date as a_birth_date, a.created_date AS a_created_date, " +
                    "a.modified_date AS a_modified_date, g.id AS g_id, g.title AS g_title, " +
                    "g.created_date AS g_created_date, g.modified_date AS g_modified_date " +
                    "FROM t_book b " +
                    "LEFT JOIN t_book_author ba ON b.id = ba.BOOK_ID " +
                    "LEFT JOIN t_author a ON ba.author_id = a.id " +
                    "LEFT JOIN t_book_genre bg ON b.id = bg.book_id " +
                    "LEFT JOIN t_genre g ON bg.genre_id = g.id " +
                    "WHERE b.id = :id ",
                params,
                new BookExtractor()
        );
        if (books == null || books.isEmpty()) {
            throw new EmptyResultDataAccessException(1);
        }
        return books.get(0);
    }

    @Override
    public List<Book> getAll() {
        return jdbc.query(
                "SELECT b.id AS b_id, b.title AS b_title, b.created_date AS b_created_date, " +
                    "b.modified_date AS b_modified_date, a.id AS a_id, a.first_name AS a_first_name, " +
                    "a.last_name AS a_last_name, a.birth_date as a_birth_date, a.created_date AS a_created_date, " +
                    "a.modified_date AS a_modified_date, g.id AS g_id, g.title AS g_title, " +
                    "g.created_date AS g_created_date, g.modified_date AS g_modified_date " +
                    "FROM t_book b " +
                    "LEFT JOIN t_book_author ba ON b.id = ba.BOOK_ID " +
                    "LEFT JOIN t_author a ON ba.author_id = a.id " +
                    "LEFT JOIN t_book_genre bg ON b.id = bg.book_id " +
                    "LEFT JOIN t_genre g ON bg.genre_id = g.id ",
                new BookExtractor()
        );
    }

    @Override
    public void deleteById(long id) {
        Map<String, Object> params = Collections.singletonMap("id", id);
        namedParamJdbcOps.update("DELETE FROM t_book WHERE id = :id", params);
    }

    @Override
    public int countAll() {
        Integer count = jdbc.queryForObject("SELECT count(*) FROM t_book", Integer.class);
        return count == null ? 0 : count;
    }

    @Override
    public int getMaxId() {
        return jdbc.queryForObject("SELECT max(id) FROM t_book", Integer.class);
    }

    private static class BookExtractor implements ResultSetExtractor<List<Book>> {

        @Override
        public List<Book> extractData(ResultSet rs) throws SQLException, DataAccessException {
            Map<Long, Book> bookMap = new HashMap<>();
            while (rs.next()) {
                long bookId = rs.getLong("b_id");
                if (!bookMap.containsKey(bookId)) {
                    bookMap.put(bookId, new Book(bookId, rs.getString("b_title"), new ArrayList<>(),
                            new ArrayList<>(), rs.getDate("b_created_date"),
                            rs.getDate("b_modified_date")));
                }
                Book book = bookMap.get(bookId);
                Author author = new Author(rs.getLong("a_id"), rs.getString("a_first_name"),
                        rs.getString("a_last_name"),  Date.from(rs.getObject("a_birth_date",
                        LocalDate.class).atStartOfDay(ZoneId.systemDefault()).toInstant()),
                        rs.getDate("a_created_date"), rs.getDate("a_modified_date"));
                if (!book.getAuthors().contains(author)) {
                    book.getAuthors().add(author);
                }
                Genre genre = new Genre(rs.getLong("g_id"), rs.getString("g_title"),
                        rs.getDate("g_created_date"), rs.getDate("g_modified_date"));
                if (!book.getGenres().contains(genre)) {
                    book.getGenres().add(genre);
                }
            }
            return bookMap.values().stream().toList();
        }
    }

    private void updateBookAuthors(Book updatedBook) {
        Book existingBook = getById(updatedBook.getId());
        List<Author> existingAuthors = existingBook.getAuthors();

        for (Author author: updatedBook.getAuthors()) {
            if (existingAuthors.contains(author)) {
                existingAuthors.remove(author);
            } else {
                namedParamJdbcOps.update(
                        "INSERT INTO t_book_author(book_id, author_id, created_date, modified_date) " +
                                "VALUES (:book_id, :author_id,  :created_date, :modified_date)",
                        Map.of("book_id", updatedBook.getId(), "author_id", author.getId(),
                                "created_date", new Date(), "modified_date", new Date())
                );
            }
        }

        for (Author author: existingAuthors) {
            namedParamJdbcOps.update(
                    "DELETE FROM t_book WHERE book_id=:book_id AND author_id=:author_id",
                    Map.of("book_id", updatedBook.getId(),"author_id", author.getId())
            );
        }
    }

    private void updateBookGenres(Book updatedBook) {
        Book existingBook = getById(updatedBook.getId());
        List<Genre> existingGenres = existingBook.getGenres();

        for (Genre genre: updatedBook.getGenres()) {
            if (existingGenres.contains(genre)) {
                existingGenres.remove(genre);
            } else {
                namedParamJdbcOps.update(
                        "INSERT INTO t_book_genre(book_id, genre_id, created_date, modified_date) " +
                                "VALUES (:book_id, :genre_id, :created_date, :modified_date)",
                        Map.of("book_id", updatedBook.getId(), "genre_id", genre.getId(),
                                "created_date", updatedBook.getCreatedDate(), "modified_date", new Date())
                );
            }
        }

        for (Genre genre: existingGenres) {
            namedParamJdbcOps.update(
                    "DELETE FROM t_book WHERE book_id=:book_id AND genre_id=:genre_id",
                    Map.of("book_id", updatedBook.getId(),"genre_id", genre.getId())
            );
        }
    }
}
