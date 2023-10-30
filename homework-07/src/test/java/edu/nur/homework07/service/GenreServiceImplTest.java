package edu.nur.homework07.service;

import edu.nur.homework07.model.Genre;
import edu.nur.homework07.repository.GenreRepository;
import org.junit.jupiter.api.Assertions;
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

@DisplayName("Genre service should ")
@SpringBootTest(properties = "spring.shell.interactive.enabled=false")
class GenreServiceImplTest {

    @MockBean
    private GenreRepository genreRepository;

    @Autowired
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
        when(genreRepository.save(any(Genre.class))).thenReturn(EXPECTED_GENRE);
        Genre saved = genreService.save(EXPECTED_GENRE.getTitle());
        assertThat(EXPECTED_GENRE).usingRecursiveComparison().isEqualTo(saved);
    }

    @DisplayName("update a genre")
    @Test
    void update() {
        when(genreRepository.save(any(Genre.class))).thenReturn(EXPECTED_GENRE);
        when(genreRepository.findById(EXPECTED_GENRE.getId())).thenReturn(Optional.of(EXPECTED_GENRE));
        Genre updated = genreService.update(EXPECTED_GENRE.getId(), EXPECTED_GENRE.getTitle());
        assertThat(EXPECTED_GENRE).usingRecursiveComparison().isEqualTo(updated);
    }

    @DisplayName("get a genre by ID")
    @Test
    void getById() {
        when(genreRepository.findById(EXPECTED_GENRE.getId())).thenReturn(Optional.of(EXPECTED_GENRE));
        Assertions.assertEquals(EXPECTED_GENRE, genreService.getById(EXPECTED_GENRE.getId()));
    }

    @DisplayName("get list of all genres")
    @Test
    void getAll() {
        when(genreRepository.findAll()).thenReturn(EXPECTED_GENRE_LIST);
        assertThat(EXPECTED_GENRE_LIST).containsExactlyElementsOf(genreService.getAll());
    }

    @DisplayName("delete a genre by ID")
    @Test
    void deleteById() {
        genreService.deleteById(EXPECTED_GENRE.getId());
        verify(genreRepository, times(1)).deleteById(EXPECTED_GENRE.getId());
    }

}