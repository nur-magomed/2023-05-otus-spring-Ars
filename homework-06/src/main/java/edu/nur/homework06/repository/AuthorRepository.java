package edu.nur.homework06.repository;

import edu.nur.homework06.model.Author;

import java.util.List;
import java.util.Optional;

public interface AuthorRepository {

    Author save(Author author);

    Optional<Author> findById(long id);

    List<Author> findAll();

    void deleteById(long id);

}
