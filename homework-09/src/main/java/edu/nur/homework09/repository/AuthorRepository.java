package edu.nur.homework09.repository;

import edu.nur.homework09.model.Author;
import org.springframework.data.repository.CrudRepository;

public interface AuthorRepository extends CrudRepository<Author, Long> {

}
