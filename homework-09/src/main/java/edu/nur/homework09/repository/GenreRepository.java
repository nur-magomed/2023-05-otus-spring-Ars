package edu.nur.homework09.repository;

import edu.nur.homework09.model.Genre;
import org.springframework.data.repository.CrudRepository;

public interface GenreRepository extends CrudRepository<Genre, Long> {

}
