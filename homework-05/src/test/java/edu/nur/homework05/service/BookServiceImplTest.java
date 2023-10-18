package edu.nur.homework05.service;

import edu.nur.homework05.dao.BookDao;
import edu.nur.homework05.model.Author;
import edu.nur.homework05.model.Book;
import edu.nur.homework05.model.Genre;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@DisplayName("Book service should ")
@SpringBootTest(properties = "spring.shell.interactive.enabled=false")
class BookServiceImplTest {

    @Mock
    private BookDao bookDao;
    @Mock
    private AuthorService authorService;
    @Mock
    private GenreService genreService;

    @InjectMocks
    private BookServiceImpl bookService;

    private final Set<Author> EXPECTED_AUTHOR_LIST = new HashSet<>();

    private final Genre GENRE = new Genre(1L, "Test genre", new Date(), new Date());

    private final Author AUTHOR = new Author(1L, "Test", "Testerov",
            new Date(), new Date(), new Date());

    private final Book EXPECTED_BOOK = new Book(1L, "Test genre", EXPECTED_AUTHOR_LIST,
            GENRE, new Date(), new Date());

    private final List<Book> EXPECTED_BOOK_LIST = new ArrayList<>();

    @BeforeEach
    void setUp() {

        EXPECTED_AUTHOR_LIST.add(AUTHOR);

        Book book2 = new Book(2L, "Test book2", EXPECTED_AUTHOR_LIST,
                GENRE, new Date(), new Date());
        Book book3 = new Book(3L, "Test book3", EXPECTED_AUTHOR_LIST,
                GENRE, new Date(), new Date());
        Book book4 = new Book(4L, "Test book4", EXPECTED_AUTHOR_LIST,
                GENRE, new Date(), new Date());

        EXPECTED_BOOK_LIST.add(book2);
        EXPECTED_BOOK_LIST.add(book3);
        EXPECTED_BOOK_LIST.add(book4);
    }

    @DisplayName("save a new book")
    @Test
    void save() {
        when(bookDao.save(any(Book.class))).thenReturn(EXPECTED_BOOK);
        Book saved = bookService.save(EXPECTED_BOOK.getTitle(), "1", "1");
        assertThat(EXPECTED_BOOK).usingRecursiveComparison().isEqualTo(saved);
    }

    @DisplayName("update a book")
    @Test
    void update() {
        when(bookDao.save(any(Book.class))).thenReturn(EXPECTED_BOOK);
        when(bookDao.getById(EXPECTED_BOOK.getId())).thenReturn(EXPECTED_BOOK);
        Book saved = bookService.update(EXPECTED_BOOK.getId(), EXPECTED_BOOK.getTitle(), "1", "1");
        assertThat(EXPECTED_BOOK).usingRecursiveComparison().isEqualTo(saved);
    }

    @DisplayName("get a book by ID")
    @Test
    void getById() {
        when(bookDao.getById(EXPECTED_BOOK.getId())).thenReturn(EXPECTED_BOOK);
        assertEquals(EXPECTED_BOOK, bookService.getById(EXPECTED_BOOK.getId()));
    }

    @DisplayName("get a list of all books")
    @Test
    void getAll() {
        when(bookDao.getAll()).thenReturn(EXPECTED_BOOK_LIST);
        assertThat(EXPECTED_BOOK_LIST).containsExactlyElementsOf(bookService.getAll());
    }

    @DisplayName("delete a book by ID")
    @Test
    void deleteById() {
        bookService.deleteById(EXPECTED_BOOK.getId());
        verify(bookDao, times(1)).deleteById(EXPECTED_BOOK.getId());
    }

    @DisplayName("get count of all books")
    @Test
    void countAll() {
        when(bookDao.countAll()).thenReturn(3);
        assertEquals(3, bookService.countAll());
    }
}