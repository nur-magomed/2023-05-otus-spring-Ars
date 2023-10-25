package edu.nur.homework09.repository;

import edu.nur.homework09.model.Book;
import org.springframework.data.repository.CrudRepository;

public interface BookRepository extends CrudRepository<Book, Long> {

}
