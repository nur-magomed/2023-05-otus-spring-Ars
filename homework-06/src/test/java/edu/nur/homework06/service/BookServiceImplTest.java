package edu.nur.homework06.service;

import edu.nur.homework06.model.Author;
import edu.nur.homework06.model.Book;
import edu.nur.homework06.model.Comment;
import edu.nur.homework06.model.Genre;
import edu.nur.homework06.repository.BookRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@DisplayName("Book service should ")
@SpringBootTest(properties = "spring.shell.interactive.enabled=false")
class BookServiceImplTest {

    @MockBean
    private BookRepository bookRepository;

    @MockBean
    private AuthorService authorService;

    @MockBean
    private GenreService genreService;

    @Autowired
    private BookServiceImpl bookService;

    private static final Set<Author> EXPECTED_AUTHOR_SET = new HashSet<>();

    private static final Author AUTHOR = new Author(1L, "Test", "Testerov",
            new Date(), new Date(), new Date());

    private static final List<Comment> EXPECTED_COMMENT_LIST = new ArrayList<>();

    private static final Comment COMMENT = new Comment(1, 1, "Test comment");

    private static final Genre GENRE = new Genre(1L, "Test genre", new Date(), new Date());

    private static final Book EXPECTED_BOOK = new Book(1L, "Test genre", GENRE, EXPECTED_AUTHOR_SET,
            EXPECTED_COMMENT_LIST, new Date(), new Date());

    private static final List<Book> EXPECTED_BOOK_LIST = new ArrayList<>();

    @BeforeEach
    void setUp() {

        EXPECTED_AUTHOR_SET.add(AUTHOR);

        EXPECTED_COMMENT_LIST.add(COMMENT);

        Book book2 = new Book(2L, "Test book2", GENRE, EXPECTED_AUTHOR_SET,
                EXPECTED_COMMENT_LIST, new Date(), new Date());
        Book book3 = new Book(3L, "Test book3", GENRE, EXPECTED_AUTHOR_SET,
                EXPECTED_COMMENT_LIST, new Date(), new Date());
        Book book4 = new Book(4L, "Test book4", GENRE, EXPECTED_AUTHOR_SET,
                EXPECTED_COMMENT_LIST, new Date(), new Date());

        EXPECTED_BOOK_LIST.add(book2);
        EXPECTED_BOOK_LIST.add(book3);
        EXPECTED_BOOK_LIST.add(book4);
    }

    @DisplayName("save a new book")
    @Test
    void save() {
        when(bookRepository.save(any(Book.class))).thenReturn(EXPECTED_BOOK);
        Book saved = bookService.save(EXPECTED_BOOK.getTitle(), "1", "1");
        assertThat(EXPECTED_BOOK).usingRecursiveComparison().isEqualTo(saved);
    }

    @DisplayName("update a book")
    @Test
    void update() {
        when(bookRepository.save(any(Book.class))).thenReturn(EXPECTED_BOOK);
        when(bookRepository.findById(EXPECTED_BOOK.getId())).thenReturn(Optional.of(EXPECTED_BOOK));
        Book saved = bookService.update(EXPECTED_BOOK.getId(), EXPECTED_BOOK.getTitle(), "1", "1");
        assertThat(EXPECTED_BOOK).usingRecursiveComparison().isEqualTo(saved);
    }

    @DisplayName("get a book by ID")
    @Test
    void getById() {
        when(bookRepository.findById(EXPECTED_BOOK.getId())).thenReturn(Optional.of(EXPECTED_BOOK));
        assertEquals(EXPECTED_BOOK, bookService.getById(EXPECTED_BOOK.getId()));
    }

    @DisplayName("get a list of all books")
    @Test
    void getAll() {
        when(bookRepository.findAll()).thenReturn(EXPECTED_BOOK_LIST);
        assertThat(EXPECTED_BOOK_LIST).containsExactlyElementsOf(bookService.getAll());
    }

    @DisplayName("delete a book by ID")
    @Test
    void deleteById() {
        bookService.deleteById(EXPECTED_BOOK.getId());
        verify(bookRepository, times(1)).deleteById(EXPECTED_BOOK.getId());
    }

}