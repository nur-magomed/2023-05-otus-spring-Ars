package edu.nur.homework06.service;

import edu.nur.homework06.model.Author;

import java.util.List;

public interface AuthorService {

    Author save(String firstName, String lastName, String birthDate);

    Author update(long id, String firstName, String lastName, String birthDate);

    Author getById(long id);

    List<Author> getAll();

    void deleteById(long id);

}
