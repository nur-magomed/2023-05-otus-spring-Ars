package edu.nur.homework06.dao;

import edu.nur.homework06.model.Author;

import java.util.List;

public interface AuthorDao {

    Author save(Author author);

    Author getById(long id);

    List<Author> getAll();

    void deleteById(long id);

    int countAll();

    List<Author> getAllUsed();

}
