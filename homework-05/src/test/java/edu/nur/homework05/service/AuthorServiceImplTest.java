package edu.nur.homework05.service;

import edu.nur.homework05.dao.AuthorDao;
import edu.nur.homework05.model.Author;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@DisplayName("Author service should ")
@SpringBootTest(properties = "spring.shell.interactive.enabled=false")
class AuthorServiceImplTest {

    @Mock
    private AuthorDao authorDao;
    @InjectMocks
    private AuthorServiceImpl authorService;

    private final Author EXP_AUTHOR = new Author(1L, "Test", "Testerov",
            new Date(), new Date(), new Date());

    private final Author AUTHOR = new Author(0L, "Test", "Testerov",
            new Date(), new Date(), new Date());
    private final String EXPECTED_BDATE = "1980-10-30";
    private final List<Author> EXPECTED_AUTHOR_LIST = new ArrayList<>();

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
        when(authorDao.save(AUTHOR)).thenReturn(EXP_AUTHOR);
        Author saved = authorService.save(EXP_AUTHOR.getFirstName(), EXP_AUTHOR.getLastName(), EXPECTED_BDATE);
        assertThat(EXP_AUTHOR).usingRecursiveComparison().isEqualTo(saved);
    }

    @DisplayName("update an author")
    @Test
    void update() {
        when(authorDao.save(EXP_AUTHOR)).thenReturn(EXP_AUTHOR);
        when(authorDao.getById(EXP_AUTHOR.getId())).thenReturn(EXP_AUTHOR);
        Author updated = authorService.update(EXP_AUTHOR.getId(), EXP_AUTHOR.getFirstName(), EXP_AUTHOR.getLastName(), EXPECTED_BDATE);
        assertThat(EXP_AUTHOR).usingRecursiveComparison().isEqualTo(updated);
    }

    @DisplayName("get an author by ID")
    @Test
    void getById() {
        when(authorDao.getById(EXP_AUTHOR.getId())).thenReturn(EXP_AUTHOR);
        assertEquals(EXP_AUTHOR, authorService.getById(EXP_AUTHOR.getId()));
    }

    @DisplayName("get a list of all authors")
    @Test
    void getAll() {
        when(authorDao.getAll()).thenReturn(EXPECTED_AUTHOR_LIST);
        assertThat(EXPECTED_AUTHOR_LIST).containsExactlyElementsOf(authorService.getAll());
    }

    @DisplayName("delete an author by ID")
    @Test
    void deleteById() {
        authorService.deleteById(EXP_AUTHOR.getId());
        verify(authorDao, times(1)).deleteById(EXP_AUTHOR.getId());
    }

    @DisplayName("get count of all authors")
    @Test
    void countAll() {
        when(authorDao.countAll()).thenReturn(3);
        assertEquals(3, authorService.countAll());
    }
}