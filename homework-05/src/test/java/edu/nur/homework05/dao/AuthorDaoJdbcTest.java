package edu.nur.homework05.dao;

import edu.nur.homework05.model.Author;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import org.springframework.dao.EmptyResultDataAccessException;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.*;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.*;

@DisplayName("Authors dao should ")
@JdbcTest
@Import(AuthorDaoJdbc.class)
class AuthorDaoJdbcTest {

    private static final int EXPECTED_AUTHORS_COUNT = 6;
    private static final int EXISTING_AUTHOR_ID = 1;
    private static final List<Long> EXISTING_AUTHOR_IDS = new ArrayList<>(Arrays.asList(1L, 2L, 3L, 4L, 5L, 6L));
    private static final String EXISTING_AUTHOR_NAME = "Aleksandr";
    private static final String EXISTING_AUTHOR_SURNAME = "Pushkin";
    private Date EXISTING_BIRTHDATE;
    private Date NOW_DATE;

    @Autowired
    private AuthorDaoJdbc authorDaoJdbc;

    @BeforeEach
    void setUp() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, 1799);
        calendar.set(Calendar.MONTH, 4);
        calendar.set(Calendar.DATE, 26);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 26);
        calendar.set(Calendar.SECOND, 43);
        calendar.set(Calendar.MILLISECOND, 0);
        EXISTING_BIRTHDATE = calendar.getTime();

        Calendar nowCalendar = Calendar.getInstance();
        nowCalendar.set(Calendar.HOUR_OF_DAY, 0);
        nowCalendar.set(Calendar.MINUTE, 0);
        nowCalendar.set(Calendar.SECOND, 0);
        nowCalendar.set(Calendar.MILLISECOND, 0);

        NOW_DATE = nowCalendar.getTime();
    }

    @DisplayName("save a new author")
    @Test
    void saveTest() {
        Author expectedAuthor = new Author( "Test", "Testerov", NOW_DATE, NOW_DATE, NOW_DATE);
        Author expectedAuthor2 = new Author( "Test2", "Testerov2", NOW_DATE, NOW_DATE, NOW_DATE);
        Author expectedAuthor3 = new Author( "Test3", "Testerov3", NOW_DATE, NOW_DATE, NOW_DATE);
        authorDaoJdbc.insert(expectedAuthor);
        authorDaoJdbc.insert(expectedAuthor2);
        authorDaoJdbc.insert(expectedAuthor3);
        Author actualAuthor = authorDaoJdbc.getById(expectedAuthor.getId());
        assertThat(actualAuthor).usingRecursiveComparison().isEqualTo(expectedAuthor);
    }

    @DisplayName("update existing author")
    @Test
    void updateTest() {
        Author expectedAuthor = authorDaoJdbc.getById(EXISTING_AUTHOR_ID);
        expectedAuthor.setLastName("Edited last name");
        authorDaoJdbc.update(expectedAuthor);
        Author actualAuthor = authorDaoJdbc.getById(EXISTING_AUTHOR_ID);
        assertThat(actualAuthor).usingRecursiveComparison().isEqualTo(expectedAuthor);
    }

    @DisplayName("get author by ID")
    @Test
    void getByIdTest() {
        Author expectedAuthor = new Author(EXISTING_AUTHOR_ID, EXISTING_AUTHOR_NAME, EXISTING_AUTHOR_SURNAME,
                EXISTING_BIRTHDATE, NOW_DATE, NOW_DATE);
        Author author = authorDaoJdbc.getById(EXISTING_AUTHOR_ID);
        assertEquals(expectedAuthor.getId(), author.getId());
        assertEquals(expectedAuthor.getFirstName(), author.getFirstName());
        assertEquals(expectedAuthor.getLastName(), author.getLastName());
    }

    @DisplayName("get list of all authors")
    @Test
    void getAllTest() {
        List<Author> allAuthors = authorDaoJdbc.getAll();
        assertEquals(allAuthors.size(), EXPECTED_AUTHORS_COUNT);
        List<Long> allAuthorsIdList = allAuthors.stream().map(Author::getId).collect(Collectors.toList());
        assertThat(allAuthorsIdList).containsAll(EXISTING_AUTHOR_IDS);
    }

    @DisplayName("delete an author by ID")
    @Test
    void deleteByIdTest() {
        assertThatCode(() -> authorDaoJdbc.getById(EXISTING_AUTHOR_ID)).doesNotThrowAnyException();
        authorDaoJdbc.deleteById(EXISTING_AUTHOR_ID);
        assertThatThrownBy(() -> authorDaoJdbc.getById(EXISTING_AUTHOR_ID)).isInstanceOf(EmptyResultDataAccessException.class);
    }

    @DisplayName("get count of all authors")
    @Test
    void countAllTest() {
        int countAll = authorDaoJdbc.countAll();
        assertEquals(countAll, EXPECTED_AUTHORS_COUNT);
    }
}