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
    private static final int EXISTING_AUTHOR_ID = 100001;
    private static final List<Long> EXISTING_AUTHOR_IDS =
            new ArrayList<>(Arrays.asList(100001L, 100002L, 100003L, 100004L,100005L, 100006L));
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

    @Test
    void saveTest() {
        Author expectedAuthor = new Author(1001, "Test", "Testerov", NOW_DATE, NOW_DATE, NOW_DATE);
        authorDaoJdbc.save(expectedAuthor);
        Author actualAuthor = authorDaoJdbc.getById(expectedAuthor.getId());
        assertThat(actualAuthor).usingRecursiveComparison().isEqualTo(expectedAuthor);
    }

    @Test
    void getByIdTest() {
        Author expectedAuthor = new Author(EXISTING_AUTHOR_ID, EXISTING_AUTHOR_NAME, EXISTING_AUTHOR_SURNAME,
                EXISTING_BIRTHDATE, NOW_DATE, NOW_DATE);
        Author author = authorDaoJdbc.getById(EXISTING_AUTHOR_ID);
        assertEquals(expectedAuthor.getId(), author.getId());
        assertEquals(expectedAuthor.getFirstName(), author.getFirstName());
        assertEquals(expectedAuthor.getLastName(), author.getLastName());
    }

    @Test
    void getAllTest() {
        List<Author> allAuthors = authorDaoJdbc.getAll();
        assertEquals(allAuthors.size(), EXPECTED_AUTHORS_COUNT);
        List<Long> allAuthorsIdList = allAuthors.stream().map(Author::getId).collect(Collectors.toList());
        assertThat(allAuthorsIdList).containsAll(EXISTING_AUTHOR_IDS);
    }

    @Test
    void deleteByIdTest() {
        assertThatCode(() -> authorDaoJdbc.getById(EXISTING_AUTHOR_ID)).doesNotThrowAnyException();
        authorDaoJdbc.deleteById(EXISTING_AUTHOR_ID);
        assertThatThrownBy(() -> authorDaoJdbc.getById(EXISTING_AUTHOR_ID)).isInstanceOf(EmptyResultDataAccessException.class);
    }

    @Test
    void countAllTest() {
        int countAll = authorDaoJdbc.countAll();
        assertEquals(countAll, EXPECTED_AUTHORS_COUNT);
    }
}