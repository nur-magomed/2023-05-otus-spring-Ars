package edu.nur.homework06.service;

import edu.nur.homework06.model.Book;

import java.util.List;

public interface BookService {

    Book save(String title, String authorIds, String genreId);

    Book update(long id, String title, String authorIds, String genreId);

    Book getById(long id);

    List<Book> getAll();

    List<Book> getAllWithAuthors();

    void deleteById(long id);

}
