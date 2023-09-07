package edu.nur.homework05.dao;

import edu.nur.homework05.model.Genre;

import java.util.List;

public interface GenreDao {

    void insert(Genre genre);

    void update(Genre genre);

    Genre getById(long id);

    List<Genre> getAll();

    void deleteById(long id);

    int countAll();

    int getMaxId();

}
