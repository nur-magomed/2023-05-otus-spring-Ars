package edu.nur.homework07.repository;

import edu.nur.homework07.model.Genre;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;

import java.util.Date;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

@DisplayName("Genre JPA repository should ")
@DataJpaTest
@Import(GenreRepositoryJpa.class)
class GenreRepositoryJpaTest {

    private static final int EXPECTED_NUMBER_OF_GENRES = 3;

    private static final long EXISTING_GENRE_ID = 1L;

    @Autowired
    private GenreRepositoryJpa repositoryJpa;

    @Autowired
    private TestEntityManager em;

    @DisplayName("save new genre")
    @Test
    void saveTest() {
        Genre genre = new Genre(0, "Test genre", new Date(), new Date());
        repositoryJpa.save(genre);
        assertThat(genre.getId()).isGreaterThan(0);

        Genre actualGenre = em.find(Genre.class, genre.getId());
        assertThat(genre).usingRecursiveComparison()
                .ignoringFields("createdDate", "modifiedDate").isEqualTo(actualGenre);
    }

    @DisplayName("update existing genre")
    @Test
    void updateTest() {
        Optional<Genre> optionalGenre = repositoryJpa.findById(1);
        Genre genre = optionalGenre.get();
        genre.setTitle("updated title");
        repositoryJpa.save(genre);
        Genre actualGenre = em.find(Genre.class, EXISTING_GENRE_ID);
        assertThat(genre).usingRecursiveComparison()
                .ignoringFields("createdDate", "modifiedDate").isEqualTo(actualGenre);
    }


    @DisplayName("find genre by ID")
    @Test
    void findByIdTest() {
        Optional<Genre> actualGenre = repositoryJpa.findById(EXISTING_GENRE_ID);
        Genre expectedGenre  = em.find(Genre.class, EXISTING_GENRE_ID);
        assertThat(actualGenre).isPresent().get().usingRecursiveComparison()
                .ignoringFields("createdDate", "modifiedDate").isEqualTo(expectedGenre);
    }

    @DisplayName("find and return a list of all genres")
    @Test
    void findAllTest() {
        var genres = repositoryJpa.findAll();
        assertThat(genres.size()).isEqualTo(EXPECTED_NUMBER_OF_GENRES);
    }

    @DisplayName("delete genre by ID")
    @Test
    void deleteByIdTest() {
        Genre existingGenre = em.find(Genre.class, EXISTING_GENRE_ID);
        assertThat(existingGenre).isNotNull();
        em.detach(existingGenre);

        repositoryJpa.deleteById(EXISTING_GENRE_ID);
        Genre deletedGenre = em.find(Genre.class, EXISTING_GENRE_ID);

        assertThat(deletedGenre).isNull();
    }
}


