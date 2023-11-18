package edu.nur.homework09.service;

import edu.nur.homework09.model.Book;

import java.util.List;
import java.util.Optional;

public interface BookService {

    List<Book> findAll();

    Optional<Book> findById(long id);

    Book save(Book book);

    void delete(Book book);

    void deleteById(long id);

}
