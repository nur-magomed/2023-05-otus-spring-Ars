package edu.nur.homework07.repository;

import edu.nur.homework07.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, Long> {

}
