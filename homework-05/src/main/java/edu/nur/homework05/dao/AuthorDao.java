package edu.nur.homework05.dao;

import edu.nur.homework05.model.Author;

import java.util.List;

public interface AuthorDao {

    Author save(Author author);

    Author update(Author author);

    Author getById(long id);

    List<Author> getAll();

    void deleteById(long id);

    int countAll();

}
