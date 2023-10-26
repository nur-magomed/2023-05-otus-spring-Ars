package edu.nur.homework05.dao;

import edu.nur.homework05.model.Author;
import edu.nur.homework05.model.Book;
import edu.nur.homework05.model.Genre;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.ResultSetExtractor;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Date;
import java.util.Set;
import java.util.HashSet;
import java.util.Objects;
import java.util.ArrayList;
import java.util.function.Function;
import java.util.stream.Collectors;

@Repository
public class BookDaoJdbc implements BookDao {

    private final AuthorDao authorDao;

    private final JdbcOperations jdbc;

    private final NamedParameterJdbcOperations namedParamJdbcOps;

    public BookDaoJdbc(AuthorDao authorDao, JdbcOperations jdbc, NamedParameterJdbcOperations namedParamJdbcOps) {
        this.authorDao = authorDao;
        this.jdbc = jdbc;
        this.namedParamJdbcOps = namedParamJdbcOps;
    }

    @Override
    public Book save(Book book) {
        if (book.getId() == 0) {
            return insert(book);
        } else {
            return update(book);
        }
    }

    private Book insert(Book book) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("title", book.getTitle());
        params.addValue("created_date", book.getCreatedDate());
        params.addValue("modified_date", new Date());
        params.addValue("genre_id", book.getGenre().getId());

        KeyHolder keyHolder = new GeneratedKeyHolder();
        namedParamJdbcOps.update("INSERT INTO t_book(title,  created_date, modified_date, genre_id) " +
                        "VALUES (:title, :created_date, :modified_date, :genre_id)", params, keyHolder);
        book.setId(Objects.requireNonNull(keyHolder.getKey()).longValue());

        Map<String, Object>[] parameters = new Map[book.getAuthors().size()];
        int index = 0;
        for (Author author: book.getAuthors()) {
            parameters[index] = Map.of("book_id", book.getId(), "author_id", author.getId(),
                    "created_date", new Date(), "modified_date", new Date());
            index++;
        }

        namedParamJdbcOps.batchUpdate("INSERT INTO t_book_author(book_id, author_id, created_date, modified_date)" +
                        "VALUES (:book_id, :author_id,  :created_date, :modified_date)", parameters);
        return book;
    }

    private Book update(Book book) {
        Date now = new Date();
        namedParamJdbcOps.update(
                "UPDATE t_book SET title=:title, modified_date=:modified_date, genre_id=:genre_id WHERE  id=:id ",
                Map.of("id", book.getId(), "title", book.getTitle(), "modified_date", now,
                        "genre_id", book.getGenre().getId()));

        updateBookAuthors(book);
        book.setModifiedDate(now);
        return book;
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
                    "LEFT JOIN t_genre g ON b.genre_id = g.id " +
                    "WHERE b.id = :id ",
                params,
                new OneBookExtractor()
        );
        if (books == null || books.isEmpty()) {
            throw new EmptyResultDataAccessException(1);
        }
        return books.get(0);
    }

    @Override
    public List<Book> getAll() {
        List<Author> authors = authorDao.getAllUsed();
        List<BookAuthorRelation> relations = getAllBookAuthorRelations();
        Map<Long, Book> books = jdbc.query(
                "SELECT b.id AS b_id, b.title AS b_title, b.created_date AS b_created_date, " +
                    "b.modified_date AS b_modified_date, g.id AS g_id, g.title AS g_title, " +
                    "g.created_date AS g_created_date, g.modified_date AS g_modified_date " +
                    "FROM t_book b " +
                    "LEFT JOIN t_genre g ON b.genre_id = g.id ",
                new BookExtractor()
        );

        mergeBookAuthors(books, authors, relations);
        return new ArrayList<>(Objects.requireNonNull(books).values());
    }

    private void mergeBookAuthors(Map<Long, Book> books, List<Author> authors, List<BookAuthorRelation> relations) {
        Map<Long, Author> authorsMap = authors.stream().collect(Collectors.toMap(Author::getId, Function.identity()));
        relations.forEach(r -> {
            if (books.containsKey(r.getBookId()) && authorsMap.containsKey(r.getAuthorId())) {
                books.get(r.getBookId()).getAuthors().add(authorsMap.get(r.getAuthorId()));
            }
        });

    }

    private List<BookAuthorRelation> getAllBookAuthorRelations() {
        return jdbc.query("SELECT book_id, author_id from t_book_author ORDER BY book_id",
                (rs, i) -> new BookAuthorRelation(rs.getLong(1), rs.getLong(2)));
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

    private static class BookExtractor implements ResultSetExtractor<Map<Long, Book>> {

        @Override
        public Map<Long, Book> extractData(ResultSet rs) throws SQLException, DataAccessException {
            Map<Long, Book> bookMap = new HashMap<>();
            while (rs.next()) {
                long bookId = rs.getLong("b_id");

                if (!bookMap.containsKey(bookId)) {
                    Genre genre = new Genre(rs.getLong("g_id"), rs.getString("g_title"),
                            rs.getDate("g_created_date"), rs.getDate("g_modified_date"));

                    bookMap.put(bookId, new Book(bookId, rs.getString("b_title"), new HashSet<>(),
                            genre, rs.getDate("b_created_date"),
                            rs.getDate("b_modified_date")));
                }
            }
            return bookMap;
        }
    }

    private void updateBookAuthors(Book updatedBook) {
        Book existingBook = getById(updatedBook.getId());
        Set<Author> existingAuthors = existingBook.getAuthors();
        List<Object> insertParamMaps = new ArrayList<>();
        for (Author author: updatedBook.getAuthors()) {
            if (!existingAuthors.remove(author)) {
                insertParamMaps.add(Map.of("book_id", updatedBook.getId(), "author_id",
                        author.getId(), "created_date", new Date(), "modified_date", new Date()));
            }
        }
        Map<String, Object>[] insertParameters = new Map[insertParamMaps.size()];
        insertParamMaps.toArray(insertParameters);
        namedParamJdbcOps.batchUpdate("INSERT INTO t_book_author(book_id, author_id, created_date, modified_date)" +
                        "VALUES (:book_id, :author_id,  :created_date, :modified_date)", insertParameters);
        Map<String, Object>[] parameters = new Map[existingAuthors.size()];
        int index = 0;
        for (Author author: existingAuthors) {
            parameters[index++] = Map.of("book_id", updatedBook.getId(), "author_id", author.getId());
        }
        namedParamJdbcOps.batchUpdate("DELETE FROM t_book_author WHERE book_id=:book_id AND author_id=:author_id",
                parameters);
    }

    private static class OneBookExtractor implements ResultSetExtractor<List<Book>> {
        @Override
        public List<Book> extractData(ResultSet rs) throws SQLException, DataAccessException {
            Map<Long, Book> bookMap = new HashMap<>();
            while (rs.next()) {
                long bookId = rs.getLong("b_id");

                if (!bookMap.containsKey(bookId)) {
                    Genre genre = new Genre(rs.getLong("g_id"), rs.getString("g_title"),
                            rs.getDate("g_created_date"), rs.getDate("g_modified_date"));

                    bookMap.put(bookId, new Book(bookId, rs.getString("b_title"), new HashSet<>(),
                            genre, rs.getDate("b_created_date"),
                            rs.getDate("b_modified_date")));
                }
                Book book = bookMap.get(bookId);
                Author author = new Author(rs.getLong("a_id"), rs.getString("a_first_name"),
                        rs.getString("a_last_name"),  Date.from(rs.getObject("a_birth_date",
                        LocalDate.class).atStartOfDay(ZoneId.systemDefault()).toInstant()),
                        rs.getDate("a_created_date"), rs.getDate("a_modified_date"));
                book.getAuthors().add(author);
            }
            return bookMap.values().stream().toList();
        }
    }


}
