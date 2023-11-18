package edu.nur.homework06.service;

import edu.nur.homework06.model.Author;
import edu.nur.homework06.repository.AuthorRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@DisplayName("Author service should ")
@SpringBootTest(properties = "spring.shell.interactive.enabled=false")
class AuthorServiceImplTest {

    @MockBean
    private AuthorRepository authorRepository;

    @Autowired
    private AuthorServiceImpl authorService;

    private static final Author EXP_AUTHOR = new Author(1L, "Test", "Testerov",
            new Date(), new Date(), new Date());

    private static final String EXPECTED_BDATE = "1980-10-30";

    private static final List<Author> EXPECTED_AUTHOR_LIST = new ArrayList<>();

    @BeforeEach
    void setUp() {
        Author author2 = new Author(2L, "Test2", "Testerov2",
                new Date(), new Date(), new Date());
        Author author3 = new Author(3L, "Test3", "Testerov3",
                new Date(), new Date(), new Date());
        Author author4 = new Author(4L, "Test4", "Testerov4",
                new Date(), new Date(), new Date());

        EXPECTED_AUTHOR_LIST.add(author2);
        EXPECTED_AUTHOR_LIST.add(author3);
        EXPECTED_AUTHOR_LIST.add(author4);
    }

    @DisplayName("save a new author")
    @Test
    void save() {
        when(authorRepository.save(any(Author.class))).thenReturn(EXP_AUTHOR);
        Author saved = authorService.save(EXP_AUTHOR.getFirstName(), EXP_AUTHOR.getLastName(), EXPECTED_BDATE);
        assertThat(EXP_AUTHOR).usingRecursiveComparison().isEqualTo(saved);
    }

    @DisplayName("update an author")
    @Test
    void update() {
        when(authorRepository.save(any(Author.class))).thenReturn(EXP_AUTHOR);
        when(authorRepository.findById(EXP_AUTHOR.getId())).thenReturn(Optional.of(EXP_AUTHOR));
        Author updated = authorService.update(EXP_AUTHOR.getId(), EXP_AUTHOR.getFirstName(), EXP_AUTHOR.getLastName(), EXPECTED_BDATE);
        assertThat(EXP_AUTHOR).usingRecursiveComparison().isEqualTo(updated);
    }

    @DisplayName("get an author by ID")
    @Test
    void getById() {
        when(authorRepository.findById(EXP_AUTHOR.getId())).thenReturn(Optional.of(EXP_AUTHOR));
        assertEquals(EXP_AUTHOR, authorService.getById(EXP_AUTHOR.getId()));
    }

    @DisplayName("get a list of all authors")
    @Test
    void getAll() {
        when(authorRepository.findAll()).thenReturn(EXPECTED_AUTHOR_LIST);
        assertThat(EXPECTED_AUTHOR_LIST).containsExactlyElementsOf(authorService.getAll());
    }

    @DisplayName("delete an author by ID")
    @Test
    void deleteById() {
        authorService.deleteById(EXP_AUTHOR.getId());
        verify(authorRepository, times(1)).deleteById(EXP_AUTHOR.getId());
    }

}