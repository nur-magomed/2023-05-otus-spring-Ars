package edu.nur.homework09.service;

import edu.nur.homework09.model.Genre;

import java.util.List;
import java.util.Optional;

public interface GenreService {

    List<Genre> findAll();

    Optional<Genre> findById(long id);

    Genre save(Genre genre);

    void delete(Genre genre);

    void deleteById(long id);

}
