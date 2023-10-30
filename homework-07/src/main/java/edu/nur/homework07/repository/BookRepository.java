package edu.nur.homework07.repository;

import edu.nur.homework07.model.Book;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {

    @EntityGraph(attributePaths = "genre")
    List<Book> findAll();

}
