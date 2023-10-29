package edu.nur.homework06.repository;

import edu.nur.homework06.model.Author;
import edu.nur.homework06.model.Book;
import edu.nur.homework06.model.Genre;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Genre JPA repository should ")
@DataJpaTest
@Import(BookRepositoryJpa.class)
class BookRepositoryJpaTest {


    @Autowired
    private BookRepositoryJpa repositoryJpa;

    @Autowired
    private TestEntityManager em;

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

        int expectedGenreId = 1;
        String expectedGenreTitle = "Prose";
        genre = new Genre(expectedGenreId, expectedGenreTitle, nowDate, nowDate);
    }


    @DisplayName("save new book")
    @Test
    void saveTest() {
        Book book = new Book(0, "Testing book", authors, genre, nowDate, nowDate);
        repositoryJpa.save(book);
        assertThat(book.getId()).isGreaterThan(0);

        Book actualBook = em.find(Book.class, book.getId());
        assertThat(actualBook).usingRecursiveComparison().isEqualTo(book);
    }

    @DisplayName("update existing book")
    @Test
    void updateTest() {
        Optional<Book> optionalAuthor = repositoryJpa.findById(1);
        Book book = optionalAuthor.get();
        book.setTitle("updatedTitle");
        repositoryJpa.save(book);
        Book actualBook = em.find(Book.class, EXISTING_BOOK_ID);
        assertThat(book).usingRecursiveComparison()
                .ignoringFields("createdDate", "modifiedDate").isEqualTo(actualBook);
    }

    @DisplayName("find book by ID")
    @Test
    void findByIdTest() {
        Optional<Book> optionalBook = repositoryJpa.findById(EXISTING_BOOK_ID);
        Book actualBook = optionalBook.get();
        Book expectedBook  = em.find(Book.class, EXISTING_BOOK_ID);
        assertEquals(expectedBook.getId(), actualBook.getId());
        assertEquals(expectedBook.getTitle(), actualBook.getTitle());
    }

    @DisplayName("find and return a list of all books")
    @Test
    void findAllTest() {
        List<Book> books = repositoryJpa.findAll();
        books.get(4).getGenre().getTitle();
        books.get(4).getAuthors().size();
        assertThat(books.size()).isEqualTo(EXPECTED_BOOK_COUNT);
    }

    @DisplayName("delete book by ID")
    @Test
    void deleteByIdTest() {
        Book existingBook = em.find(Book.class, EXISTING_BOOK_ID);
        assertThat(existingBook).isNotNull();
        em.detach(existingBook);

        repositoryJpa.deleteById(EXISTING_BOOK_ID);
        Book deletedBook = em.find(Book.class, EXISTING_BOOK_ID);

        assertThat(deletedBook).isNull();
    }

}