package edu.nur.homework06.service;

import edu.nur.homework06.dao.GenreDao;
import edu.nur.homework06.model.Genre;
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

@DisplayName("Genre service should ")
@SpringBootTest(properties = "spring.shell.interactive.enabled=false")
class GenreServiceImplTest {

    @Mock
    private GenreDao genreDao;
    @InjectMocks
    private GenreServiceImpl genreService;

    private final Genre GENRE = new Genre(0L, "Test genre", new Date(), new Date());
    private final Genre EXPECTED_GENRE = new Genre(1L, "Test genre", new Date(), new Date());

    private final List<Genre> EXPECTED_GENRE_LIST = new ArrayList<>();

    @BeforeEach
    void setUp() {
        Genre genre2 = new Genre(2L, "Test genre2", new Date(), new Date());
        Genre genre3 = new Genre(3L, "Test genre3", new Date(), new Date());
        Genre genre4 = new Genre(4L, "Test genre4", new Date(), new Date());
        EXPECTED_GENRE_LIST.add(genre2);
        EXPECTED_GENRE_LIST.add(genre3);
        EXPECTED_GENRE_LIST.add(genre4);
    }

    @DisplayName("save new genre")
    @Test
    void save() {
        when(genreDao.save(any(Genre.class))).thenReturn(EXPECTED_GENRE);
        Genre saved = genreService.save(EXPECTED_GENRE.getTitle());
        assertThat(EXPECTED_GENRE).usingRecursiveComparison().isEqualTo(saved);
    }

    @DisplayName("update a genre")
    @Test
    void update() {
        when(genreDao.save(any(Genre.class))).thenReturn(EXPECTED_GENRE);
        when(genreDao.getById(EXPECTED_GENRE.getId())).thenReturn(EXPECTED_GENRE);
        Genre updated = genreService.update(EXPECTED_GENRE.getId(), EXPECTED_GENRE.getTitle());
        assertThat(EXPECTED_GENRE).usingRecursiveComparison().isEqualTo(updated);
    }

    @DisplayName("get a genre by ID")
    @Test
    void getById() {
        when(genreDao.getById(EXPECTED_GENRE.getId())).thenReturn(EXPECTED_GENRE);
        assertEquals(EXPECTED_GENRE, genreService.getById(EXPECTED_GENRE.getId()));
    }

    @DisplayName("get list of all genres")
    @Test
    void getAll() {
        when(genreDao.getAll()).thenReturn(EXPECTED_GENRE_LIST);
        assertThat(EXPECTED_GENRE_LIST).containsExactlyElementsOf(genreService.getAll());
    }

    @DisplayName("delete a genre by ID")
    @Test
    void deleteById() {
        genreService.deleteById(EXPECTED_GENRE.getId());
        verify(genreDao, times(1)).deleteById(EXPECTED_GENRE.getId());
    }

    @DisplayName("get count of all genres")
    @Test
    void countAll() {
        when(genreDao.countAll()).thenReturn(3);
        assertEquals(3, genreService.countAll());
    }
}