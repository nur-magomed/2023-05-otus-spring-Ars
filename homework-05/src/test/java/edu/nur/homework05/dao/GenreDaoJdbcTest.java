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

@DisplayName("Genre dao should ")
@JdbcTest
@Import(GenreDaoJdbc.class)
public class GenreDaoJdbcTest {

    @Autowired
    private GenreDaoJdbc genreDaoJdbc;

    private static final int EXPECTED_GENRE_COUNT = 3;

    private static final List<Long> EXISTING_GENRE_IDS = new ArrayList<>(Arrays.asList(100001L, 100002L, 100003L));

    private final int EXPECTED_GENRE_ID = 1001;

    private final int EXISTING_GENRE_ID = 100001;

    private final String EXISTING_GENRE_TITLE = "Prose";

    private Date NOW_DATE;

    @BeforeEach
    void setUp() {
        Calendar nowCalendar = Calendar.getInstance();
        nowCalendar.set(Calendar.HOUR_OF_DAY, 0);
        nowCalendar.set(Calendar.MINUTE, 0);
        nowCalendar.set(Calendar.SECOND, 0);
        nowCalendar.set(Calendar.MILLISECOND, 0);
        NOW_DATE = nowCalendar.getTime();
    }


    @Test
    void saveTest() {
        Genre expectedGenre = new Genre(EXPECTED_GENRE_ID, "TEST Genre", NOW_DATE, NOW_DATE);
        genreDaoJdbc.save(expectedGenre);
        Genre actualGenre = genreDaoJdbc.getById(expectedGenre.getId());
        assertThat(actualGenre).usingRecursiveComparison().isEqualTo(expectedGenre);
    }

    @Test
    void getByIdTest() {
        Genre expectedGenre = new Genre(EXISTING_GENRE_ID, EXISTING_GENRE_TITLE, NOW_DATE, NOW_DATE);
        Genre genre = genreDaoJdbc.getById(EXISTING_GENRE_ID);
        assertEquals(expectedGenre.getId(), genre.getId());
        assertEquals(expectedGenre.getTitle(), genre.getTitle());
    }

    @Test
    void getAllTest() {
        List<Genre> allGenres = genreDaoJdbc.getAll();
        assertEquals(allGenres.size(), EXPECTED_GENRE_COUNT);
        List<Long> allAuthorsIdList = allGenres.stream().map(Genre::getId).collect(Collectors.toList());
        assertThat(allAuthorsIdList).containsAll(EXISTING_GENRE_IDS);
    }

    @Test
    void deleteByIdTest() {
        assertThatCode(() -> genreDaoJdbc.getById(EXISTING_GENRE_ID)).doesNotThrowAnyException();
        genreDaoJdbc.deleteById(EXISTING_GENRE_ID);
        assertThatThrownBy(() -> genreDaoJdbc.getById(EXISTING_GENRE_ID)).isInstanceOf(EmptyResultDataAccessException.class);
    }

    @Test
    void countAllTest() {
        int countAll = genreDaoJdbc.countAll();
        assertEquals(countAll, EXPECTED_GENRE_COUNT);
    }
}
