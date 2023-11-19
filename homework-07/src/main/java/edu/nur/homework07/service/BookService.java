package edu.nur.homework07.service;

import edu.nur.homework07.dto.BookDto;
import edu.nur.homework07.model.Book;

import java.util.List;

public interface BookService {

    Book save(String title, String authorIds, String genreId);

    Book update(long id, String title, String authorIds, String genreId);

    Book getById(long id);

    List<Book> getAll();

    List<BookDto> getAllWithAuthors();

    void deleteById(long id);

}
