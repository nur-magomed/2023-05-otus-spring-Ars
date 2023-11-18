package edu.nur.homework09.controller;

import edu.nur.homework09.model.Author;
import edu.nur.homework09.model.Book;
import edu.nur.homework09.model.Genre;
import edu.nur.homework09.service.AuthorService;
import edu.nur.homework09.service.BookService;
import edu.nur.homework09.service.GenreService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.*;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;

@WebMvcTest(BookController.class)
class BookControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private BookService bookService;

    @MockBean
    private GenreService genreService;

    @MockBean
    private AuthorService authorService;

    private final Set<Author> authorSet = new HashSet<>();
    private final List<Author> authorsList = new ArrayList<>();

    private final List<Genre> genres = new ArrayList<>();

    private Genre genre;

    @BeforeEach
     void setUp() {
        authorSet.add(new Author(1, "Name1", "LastName1"));
        authorSet.add(new Author(2, "Name2", "LastName2"));

        authorsList.add(new Author(2, "Name2", "LastName2"));
        authorsList.add(new Author(2, "Name2", "LastName2"));

        genres.add(new Genre(1, "Genre1"));
        genres.add(new Genre(2, "Genre2"));

        genre = new Genre(1, "Genre");
    }

    @Test
    void listGenres() throws Exception {
        List<Book> expectedBooks = new ArrayList<>();
        expectedBooks.add(new Book(1, "Book1", authorSet, genre));
        expectedBooks.add(new Book(2, "Book2", authorSet, genre));

        given(bookService.findAll()).willReturn(expectedBooks);

        mvc.perform(get("/books"))
                .andExpect(status().isOk())
                .andExpect(model().size(1))
                .andExpect(view().name("book_list"))
                .andExpect(model().attributeExists("books"))
                .andExpect(model().attribute("books", expectedBooks));
    }

    @Test
    void editPage() throws Exception {
        Book book = new Book(1, "Book1", authorSet, genre);
        given(bookService.findById(1)).willReturn(Optional.of(book));
        given(authorService.findAll()).willReturn(authorsList);
        given(genreService.findAll()).willReturn(genres);

        mvc.perform(get("/book/edit").param("id", "1"))
                .andExpect(status().isOk())
                .andExpect(model().size(3))
                .andExpect(view().name("book_edit"))
                .andExpect(model().attributeExists("book", "allAuthors", "allGenres"))
                .andExpect(model().attribute("book", book))
                .andExpect(model().attribute("allAuthors", authorsList))
                .andExpect(model().attribute("allGenres", genres));

    }

    @Test
    void saveBook() throws Exception {
        Book book = new Book(1, "Book1", authorSet, genre);
        given(bookService.save(book)).willReturn(book);

        mvc.perform(post("/book/edit")
                        .param("id-input", "1")
                        .param("book-title-input", "Book1")
                        .param("genre", "1")
                        .param("authors", "1,2"))
                .andExpect(status().isOk())
                .andExpect(model().size(3))
                .andExpect(view().name("book_edit"))
                .andDo(print());
    }

    @Test
    void deleteBook() throws Exception {
        mvc.perform(post("/book/delete/1"))
                .andExpect(status().is3xxRedirection());
        verify(bookService, times(1)).deleteById(1L);
    }
}