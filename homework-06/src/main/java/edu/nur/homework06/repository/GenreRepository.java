package edu.nur.homework06.repository;

import edu.nur.homework06.model.Genre;

import java.util.List;
import java.util.Optional;

public interface GenreRepository {

    Genre save(Genre genre);

    Optional<Genre> findById(long id);

    List<Genre> findAll();

    void deleteById(long id);

}
