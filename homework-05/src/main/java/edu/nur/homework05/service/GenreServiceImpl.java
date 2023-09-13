package edu.nur.homework05.service;

import edu.nur.homework05.dao.GenreDao;
import edu.nur.homework05.model.Genre;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GenreServiceImpl implements GenreService {

    private final GenreDao genreDao;

    public GenreServiceImpl(GenreDao genreDao) {
        this.genreDao = genreDao;
    }

    @Override
    public Genre save(Genre genre) {
        return genreDao.save(genre);
    }

    @Override
    public Genre update(Genre genre) {
        return genreDao.update(genre);
    }

    @Override
    public Genre getById(long id) {
        return genreDao.getById(id);
    }

    @Override
    public List<Genre> getAll() {
        return genreDao.getAll();
    }

    @Override
    public void deleteById(long id) {
        genreDao.deleteById(id);
    }

    @Override
    public int countAll() {
        return genreDao.countAll();
    }
}
