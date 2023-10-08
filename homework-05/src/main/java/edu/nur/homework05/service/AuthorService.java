package edu.nur.homework05.service;

import edu.nur.homework05.model.Author;

import java.util.List;

public interface AuthorService {

    Author save(String firstName, String lastName, String birthDate);

    Author update(long id, String firstName, String lastName, String birthDate);

    Author getById(long id);

    List<Author> getAll();

    void printById(long id);

    void printAll();

    void deleteById(long id);

    int countAll();

}
