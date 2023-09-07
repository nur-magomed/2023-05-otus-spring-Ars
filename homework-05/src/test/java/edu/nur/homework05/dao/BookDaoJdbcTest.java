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
@Import(BookDaoJdbc.class)
public class BookDaoJdbcTest {

    @Autowired
    private BookDaoJdbc bookDaoJdbc;

    private final int EXISTING_AUTHOR_ID = 100001;
    private final int EXISTING_BOOK_ID = 100001;

    private final List<Long> EXISTING_BOOK_IDS = new ArrayList<>(Arrays.asList(100001L, 100002L, 100003L, 100004L, 100005L));

    private final String EXISTING_AUTHOR_NAME = "Aleksandr";

    private final String EXISTING_AUTHOR_SURNAME = "Pushkin";

    private final int EXPECTED_BOOK_ID = 100001;

    private final String EXPECTED_BOOK_TITLE = "A hero of our time";

    private Date EXISTING_BIRTHDATE;

    private final int EXPECTED_BOOK_COUNT = 5;

    private final int EXPECTED_GENRE_ID = 100001;

    private final String EXPECTED_GENRE_TITLE = "Prose";

    private Date NOW_DATE;

    private final List<Author> authors = new ArrayList<>();

    private final List<Genre> genres = new ArrayList<>();

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
        EXISTING_BIRTHDATE = calendar.getTime();

        Calendar nowCalendar = Calendar.getInstance();
        nowCalendar.set(Calendar.HOUR_OF_DAY, 0);
        nowCalendar.set(Calendar.MINUTE, 0);
        nowCalendar.set(Calendar.SECOND, 0);
        nowCalendar.set(Calendar.MILLISECOND, 0);
        NOW_DATE = nowCalendar.getTime();

        Author expectingAuthor = new Author(EXISTING_AUTHOR_ID, EXISTING_AUTHOR_NAME, EXISTING_AUTHOR_SURNAME,
                EXISTING_BIRTHDATE, NOW_DATE, NOW_DATE);
        authors.add(expectingAuthor);

        Genre genre = new Genre(EXPECTED_GENRE_ID, EXPECTED_GENRE_TITLE, NOW_DATE, NOW_DATE);
        genres.add(genre);
    }


    @Test
    void saveTest() {
        Book expectedBook = new Book(1001, "Testing book", authors, genres, NOW_DATE, NOW_DATE);
        bookDaoJdbc.save(expectedBook);
        Book actualBook = bookDaoJdbc.getById(expectedBook.getId());
        assertThat(actualBook).usingRecursiveComparison().isEqualTo(expectedBook);
    }

    @Test
    void getByIdTest() {
        Book expectedBook = new Book(EXPECTED_BOOK_ID, EXPECTED_BOOK_TITLE, authors, genres, NOW_DATE, NOW_DATE);
        Book book = bookDaoJdbc.getById(EXISTING_AUTHOR_ID);
        assertEquals(expectedBook.getId(), book.getId());
        assertEquals(expectedBook.getTitle(), book.getTitle());
    }

    @Test
    void getAllTest() {
        List<Book> allBooks = bookDaoJdbc.getAll();
        assertEquals(allBooks.size(), EXPECTED_BOOK_COUNT);
        List<Long> allBooksIdList = allBooks.stream().map(Book::getId).collect(Collectors.toList());
        assertThat(allBooksIdList).containsAll(EXISTING_BOOK_IDS);
    }

    @Test
    void deleteByIdTest() {
        assertThatCode(() -> bookDaoJdbc.getById(EXISTING_BOOK_ID)).doesNotThrowAnyException();
        bookDaoJdbc.deleteById(EXISTING_BOOK_ID);
        assertThatThrownBy(() -> bookDaoJdbc.getById(EXISTING_BOOK_ID)).isInstanceOf(EmptyResultDataAccessException.class);
    }

    @Test
    void countAllTest() {
        int countAll = bookDaoJdbc.countAll();
        assertEquals(countAll, EXPECTED_BOOK_COUNT);
    }
}
