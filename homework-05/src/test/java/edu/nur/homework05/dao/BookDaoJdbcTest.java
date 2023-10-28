package edu.nur.homework05.dao;

import edu.nur.homework05.model.Author;
import edu.nur.homework05.model.Book;
import edu.nur.homework05.model.Genre;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import org.springframework.dao.EmptyResultDataAccessException;

import java.util.*;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Book dao should ")
@JdbcTest
@Import({AuthorDaoJdbc.class, BookDaoJdbc.class})
public class BookDaoJdbcTest {

    @Autowired
    private BookDaoJdbc bookDaoJdbc;
    @Autowired
    private AuthorDaoJdbc authorDaoJdbc;

    private final int EXISTING_AUTHOR_ID = 1;
    private final int EXISTING_BOOK_ID = 1;
    private final List<Long> EXISTING_BOOK_IDS = new ArrayList<>(Arrays.asList(1L, 2L, 3L, 4L, 5L));
    private final int EXPECTED_BOOK_ID = 1;
    private final int EXPECTED_BOOK_COUNT = 5;
    private Date nowDate;
    private final Set<Author> authors = new HashSet<>();
    private Genre genre;

    @BeforeEach
    void setUp() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, 1799);
        calendar.set(Calendar.MONTH, 4);
        calendar.set(Calendar.DATE, 26);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 29);
        calendar.set(Calendar.SECOND, 43);
        calendar.set(Calendar.MILLISECOND, 0);
        Date EXISTING_BIRTHDATE = calendar.getTime();

        Calendar nowCalendar = Calendar.getInstance();
        nowCalendar.set(Calendar.HOUR_OF_DAY, 0);
        nowCalendar.set(Calendar.MINUTE, 0);
        nowCalendar.set(Calendar.SECOND, 0);
        nowCalendar.set(Calendar.MILLISECOND, 0);
        nowDate = nowCalendar.getTime();

        String EXISTING_AUTHOR_NAME = "Aleksandr";
        String EXISTING_AUTHOR_SURNAME = "Pushkin";
        Author expectingAuthor = new Author(EXISTING_AUTHOR_ID, EXISTING_AUTHOR_NAME, EXISTING_AUTHOR_SURNAME,
                EXISTING_BIRTHDATE, nowDate, nowDate);
        authors.add(expectingAuthor);

        int EXPECTED_GENRE_ID = 1;
        String EXPECTED_GENRE_TITLE = "Prose";
        genre = new Genre(EXPECTED_GENRE_ID, EXPECTED_GENRE_TITLE, nowDate, nowDate);
    }

    @DisplayName("save a new book")
    @Test
    void saveTest() {
        Book saved = bookDaoJdbc.save(new Book(0, "Testing book", authors, genre, nowDate, nowDate));
        Book actualBook = bookDaoJdbc.getById(saved.getId());
        assertThat(actualBook).usingRecursiveComparison().isEqualTo(saved);
    }

    @DisplayName("update existing book")
    @Test
    void updateTest() {
        Book expectedBook = bookDaoJdbc.getById(EXPECTED_BOOK_ID);
        expectedBook.setTitle("New title");
        expectedBook.getAuthors().add(authorDaoJdbc.getById(6));
        expectedBook = bookDaoJdbc.save(expectedBook);

        Book actualBook = bookDaoJdbc.getById(EXPECTED_BOOK_ID);
        assertThat(actualBook).usingRecursiveComparison().ignoringFields("modifiedDate").isEqualTo(expectedBook);
    }

    @DisplayName("get a book by ID")
    @Test
    void getByIdTest() {
        String EXPECTED_BOOK_TITLE = "A hero of our time";
        Book expectedBook = new Book(EXPECTED_BOOK_ID, EXPECTED_BOOK_TITLE, authors, genre, nowDate, nowDate);
        Book book = bookDaoJdbc.getById(EXISTING_AUTHOR_ID);
        assertEquals(expectedBook.getId(), book.getId());
        assertEquals(expectedBook.getTitle(), book.getTitle());
    }

    @DisplayName("get list of all books")
    @Test
    void getAllTest() {
        List<Book> allBooks = bookDaoJdbc.getAll();
        assertEquals(allBooks.size(), EXPECTED_BOOK_COUNT);
        List<Long> allBooksIdList = allBooks.stream().map(Book::getId).collect(Collectors.toList());
        assertThat(allBooksIdList).containsAll(EXISTING_BOOK_IDS);
    }

    @DisplayName("delete a book by ID")
    @Test
    void deleteByIdTest() {
        assertThatCode(() -> bookDaoJdbc.getById(EXISTING_BOOK_ID)).doesNotThrowAnyException();
        bookDaoJdbc.deleteById(EXISTING_BOOK_ID);
        assertThatThrownBy(() -> bookDaoJdbc.getById(EXISTING_BOOK_ID)).isInstanceOf(EmptyResultDataAccessException.class);
    }

    @DisplayName("get count of all books")
    @Test
    void countAllTest() {
        int countAll = bookDaoJdbc.countAll();
        assertEquals(countAll, EXPECTED_BOOK_COUNT);
    }
}
