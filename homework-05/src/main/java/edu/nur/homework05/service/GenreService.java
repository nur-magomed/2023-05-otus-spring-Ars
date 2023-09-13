package edu.nur.homework05.service;

import edu.nur.homework05.model.Genre;

import java.util.List;

public interface GenreService {

    Genre save(Genre genre);

    Genre update(Genre genre);

    Genre getById(long id);

    List<Genre> getAll();

    void deleteById(long id);

    int countAll();

}
