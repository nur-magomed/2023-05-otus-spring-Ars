package edu.nur.homework07.repository;

import edu.nur.homework07.model.Genre;

import java.util.List;
import java.util.Optional;

public interface GenreRepository {

    Genre save(Genre genre);

    Optional<Genre> findById(long id);

    List<Genre> findAll();

    void deleteById(long id);

}
