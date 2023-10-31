package edu.nur.homework09.service;

import edu.nur.homework09.model.Author;

import java.util.List;
import java.util.Optional;

public interface AuthorService {

    List<Author> findAll();

    Optional<Author> findById(long id);

    Author save(Author author);

    void delete(Author author);

    void deleteById(long id);

}
