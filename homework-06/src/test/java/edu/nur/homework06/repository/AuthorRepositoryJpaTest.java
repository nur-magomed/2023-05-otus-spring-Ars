package edu.nur.homework06.repository;

import edu.nur.homework06.model.Author;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;

import java.util.Date;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Genre JPA repository should ")
@DataJpaTest
@Import(AuthorRepositoryJpa.class)
class AuthorRepositoryJpaTest {

    private static final int EXPECTED_NUMBER_OF_AUTHORS = 6;

    private static final long EXISTING_AUTHOR_ID = 1L;


    @Autowired
    private AuthorRepositoryJpa repositoryJpa;

    @Autowired
    private TestEntityManager em;

    @DisplayName("save new author")
    @Test
    void saveTest() {
        Author author = new Author(0, "FirstName", "LastName", new Date(), new Date(), new Date());
        repositoryJpa.save(author);
        assertThat(author.getId()).isGreaterThan(0);

        Author actualAuthor = em.find(Author.class, author.getId());
        assertThat(author).usingRecursiveComparison()
                .ignoringFields("createdDate", "modifiedDate").isEqualTo(actualAuthor);
    }

    @DisplayName("update existing author")
    @Test
    void updateTest() {
        Optional<Author> optionalAuthor = repositoryJpa.findById(1);
        Author author = optionalAuthor.get();
        author.setFirstName("updatedFName");
        author.setFirstName("updatedLName");
        repositoryJpa.save(author);
        Author actualAuthor = em.find(Author.class, EXISTING_AUTHOR_ID);
        assertThat(author).usingRecursiveComparison()
                .ignoringFields("createdDate", "modifiedDate").isEqualTo(actualAuthor);
    }

    @DisplayName("find author by ID")
    @Test
    void findByIdTest() {
        Optional<Author> actualAuthor = repositoryJpa.findById(EXISTING_AUTHOR_ID);
        Author expectedAuthor  = em.find(Author.class, EXISTING_AUTHOR_ID);
        assertThat(actualAuthor).isPresent().get().usingRecursiveComparison()
                .ignoringFields("createdDate", "modifiedDate").isEqualTo(expectedAuthor);
    }

    @DisplayName("find and return a list of all authors")
    @Test
    void findAllTest() {
        var authors = repositoryJpa.findAll();
        assertThat(authors.size()).isEqualTo(EXPECTED_NUMBER_OF_AUTHORS);
    }

    @DisplayName("delete author by ID")
    @Test
    void deleteByIdTest() {
        Author existingAuthor = em.find(Author.class, EXISTING_AUTHOR_ID);
        assertThat(existingAuthor).isNotNull();
        em.detach(existingAuthor);

        repositoryJpa.deleteById(EXISTING_AUTHOR_ID);
        Author deletedAuthor = em.find(Author.class, EXISTING_AUTHOR_ID);

        assertThat(deletedAuthor).isNull();
    }
}