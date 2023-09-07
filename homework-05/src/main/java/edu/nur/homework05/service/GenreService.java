package edu.nur.homework05.service;

import edu.nur.homework05.model.Genre;

import java.util.List;

public interface GenreService {

    void save(Genre genre);

    void update(Genre genre);

    Genre getById(long id);

    List<Genre> getAll();

    void deleteById(long id);

    int countAll();

}
