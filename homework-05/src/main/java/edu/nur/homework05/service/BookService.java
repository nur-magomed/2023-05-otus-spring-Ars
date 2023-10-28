package edu.nur.homework05.service;

import edu.nur.homework05.model.Book;

import java.util.List;

public interface BookService {

    Book save(String title, String authorIds, String genreId);

    Book update(long id, String title, String authorIds, String genreId);

    Book getById(long id);

    List<Book> getAll();

    void printById(long id);

    void printAll();

    void deleteById(long id);

    int countAll();

}
