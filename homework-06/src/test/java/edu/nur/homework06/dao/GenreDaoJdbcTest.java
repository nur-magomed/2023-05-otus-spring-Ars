package edu.nur.homework06.dao;

import edu.nur.homework06.model.Genre;
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
    private static final List<Long> EXISTING_GENRE_IDS = new ArrayList<>(Arrays.asList(1L, 2L, 3L));
    private final int EXISTING_GENRE_ID = 1;
    private Date nowDate;

    @BeforeEach
    void setUp() {
        Calendar nowCalendar = Calendar.getInstance();
        nowCalendar.set(Calendar.HOUR_OF_DAY, 0);
        nowCalendar.set(Calendar.MINUTE, 0);
        nowCalendar.set(Calendar.SECOND, 0);
        nowCalendar.set(Calendar.MILLISECOND, 0);
        nowDate = nowCalendar.getTime();
    }

    @DisplayName("save new genre")
    @Test
    void saveTest() {
        Genre saved = genreDaoJdbc.save(new Genre(0, "TEST Genre", nowDate, nowDate));
        Genre actualGenre = genreDaoJdbc.getById(saved.getId());
        assertThat(actualGenre).usingRecursiveComparison()
                .ignoringFields("createdDate", "modifiedDate").isEqualTo(saved);
    }

    @DisplayName("update existing genre")
    @Test
    void updateTest() {
        Genre expectedGenre = genreDaoJdbc.getById(EXISTING_GENRE_ID);
        expectedGenre.setTitle("Edited title");
        expectedGenre = genreDaoJdbc.save(expectedGenre);

        Genre actualGenre = genreDaoJdbc.getById(EXISTING_GENRE_ID);
        assertThat(actualGenre).usingRecursiveComparison()
                .ignoringFields("createdDate", "modifiedDate").isEqualTo(expectedGenre);
    }

    @DisplayName("get genre by ID")
    @Test
    void getByIdTest() {
        String EXISTING_GENRE_TITLE = "Prose";
        Genre expectedGenre = new Genre(EXISTING_GENRE_ID, EXISTING_GENRE_TITLE, nowDate, nowDate);
        Genre genre = genreDaoJdbc.getById(EXISTING_GENRE_ID);
        assertEquals(expectedGenre.getId(), genre.getId());
        assertEquals(expectedGenre.getTitle(), genre.getTitle());
    }

    @DisplayName("get list of all genres")
    @Test
    void getAllTest() {
        List<Genre> allGenres = genreDaoJdbc.getAll();
        assertEquals(allGenres.size(), EXPECTED_GENRE_COUNT);
        List<Long> allAuthorsIdList = allGenres.stream().map(Genre::getId).collect(Collectors.toList());
        assertThat(allAuthorsIdList).containsAll(EXISTING_GENRE_IDS);
    }

    @DisplayName("delete genre by ID")
    @Test
    void deleteByIdTest() {
        assertThatCode(() -> genreDaoJdbc.getById(EXISTING_GENRE_ID)).doesNotThrowAnyException();
        genreDaoJdbc.deleteById(EXISTING_GENRE_ID);
        assertThatThrownBy(() -> genreDaoJdbc.getById(EXISTING_GENRE_ID)).isInstanceOf(EmptyResultDataAccessException.class);
    }

    @DisplayName("get count of genres")
    @Test
    void countAllTest() {
        int countAll = genreDaoJdbc.countAll();
        assertEquals(countAll, EXPECTED_GENRE_COUNT);
    }
}
