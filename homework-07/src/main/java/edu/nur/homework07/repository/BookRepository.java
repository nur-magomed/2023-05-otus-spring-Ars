package edu.nur.homework07.repository;

import edu.nur.homework07.model.Book;

import java.util.List;
import java.util.Optional;

public interface BookRepository {

    Book save(Book book);

    Optional<Book> findById(long id);

    List<Book> findAll();

    void deleteById(long id);

}
