package edu.nur.homework06.repository;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;

@DisplayName("Genre JPA repository should ")
@DataJpaTest
@Import(AuthorRepositoryJpa.class)
public class CommentRepositoryJpaTest {

    @Autowired
    private AuthorRepositoryJpa repositoryJpa;

    @Autowired
    private TestEntityManager em;

    @DisplayName("save new author")
    @Test
    void saveTest() {
    }

    @DisplayName("update existing author")
    @Test
    void updateTest() {

    }

    @DisplayName("find author by ID")
    @Test
    void findByIdTest() {
    }

    @DisplayName("find and return a list of all authors")
    @Test
    void findAllTest() {
    }

    @DisplayName("delete author by ID")
    @Test
    void deleteByIdTest() {
    }

}
