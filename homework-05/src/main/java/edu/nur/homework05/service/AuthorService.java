package edu.nur.homework05.service;

import edu.nur.homework05.model.Author;

import java.util.List;

public interface AuthorService {

    Author save(Author author);

    Author update(Author author);

    Author getById(long id);

    List<Author> getAll();

    void deleteById(long id);

    int countAll();

}
