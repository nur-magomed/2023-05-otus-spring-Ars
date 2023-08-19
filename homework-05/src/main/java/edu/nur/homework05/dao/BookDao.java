package edu.nur.homework05.dao;

import edu.nur.homework05.model.Book;

import java.util.List;

public interface BookDao {

    void save(Book book);

    Book getById(long id);

    List<Book> getAll();

    void deleteById(long id);
}
