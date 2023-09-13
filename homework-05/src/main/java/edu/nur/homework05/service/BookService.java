package edu.nur.homework05.service;

import edu.nur.homework05.model.Book;

import java.util.List;

public interface BookService {

    Book save(Book book);

    Book update(Book book);

    Book getById(long id);

    List<Book> getAll();

    void deleteById(long id);

    int countAll();

}
