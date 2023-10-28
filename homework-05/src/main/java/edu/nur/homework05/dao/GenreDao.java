package edu.nur.homework05.dao;

import edu.nur.homework05.model.Genre;

import java.util.List;

public interface GenreDao {

    Genre save(Genre genre);

    Genre getById(long id);

    List<Genre> getAll();

    void deleteById(long id);

    int countAll();

}
