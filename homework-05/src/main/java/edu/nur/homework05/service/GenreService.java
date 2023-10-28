package edu.nur.homework05.service;

import edu.nur.homework05.model.Genre;

import java.util.List;

public interface GenreService {

    Genre save(String title);

    Genre update(long id, String title);

    Genre getById(long id);

    List<Genre> getAll();

    void deleteById(long id);

    int countAll();

}
