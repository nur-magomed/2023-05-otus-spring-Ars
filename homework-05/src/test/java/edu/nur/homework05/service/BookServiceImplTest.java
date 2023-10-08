package edu.nur.homework05.service;

import edu.nur.homework05.dao.BookDao;
import edu.nur.homework05.model.Author;
import edu.nur.homework05.model.Book;
import edu.nur.homework05.model.Genre;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@DisplayName("Book service should ")
@ExtendWith(MockitoExtension.class)
class BookServiceImplTest {

    @Mock
    private BookDao bookDao;
    @InjectMocks
    private BookServiceImpl bookService;

    private final List<Author> EXPECTED_AUTHOR_LIST = new ArrayList<>();

    private final List<Genre> EXPECTED_GENRE_LIST = new ArrayList<>();

    private final Book EXPECTED_BOOK = new Book(1L, "Test genre", EXPECTED_AUTHOR_LIST,
            null, new Date(), new Date());

    private final List<Book> EXPECTED_BOOK_LIST = new ArrayList<>();

    @BeforeEach
    void setUp() {
        Genre genre = new Genre(1L, "Test genre", new Date(), new Date());
        EXPECTED_GENRE_LIST.add(genre);
        Author author = new Author(1L, "Test", "Testerov",
                new Date(), new Date(), new Date());
        EXPECTED_AUTHOR_LIST.add(author);

        Book book2 = new Book(2L, "Test book2", EXPECTED_AUTHOR_LIST,
                genre, new Date(), new Date());
        Book book3 = new Book(3L, "Test book3", EXPECTED_AUTHOR_LIST,
                genre, new Date(), new Date());
        Book book4 = new Book(4L, "Test book4", EXPECTED_AUTHOR_LIST,
                genre, new Date(), new Date());

        EXPECTED_BOOK_LIST.add(book2);
        EXPECTED_BOOK_LIST.add(book3);
        EXPECTED_BOOK_LIST.add(book4);
    }

    @DisplayName("save a new book")
    @Test
    void save() {
        bookService.save(EXPECTED_BOOK.getTitle(), "1", "1");
        verify(bookDao, times(1)).save(EXPECTED_BOOK);
    }

    @DisplayName("update a book")
    @Test
    void update() {
        bookService.update(EXPECTED_BOOK.getId(), EXPECTED_BOOK.getTitle(), "1", "1");
        verify(bookDao, times(1)).save(EXPECTED_BOOK);
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