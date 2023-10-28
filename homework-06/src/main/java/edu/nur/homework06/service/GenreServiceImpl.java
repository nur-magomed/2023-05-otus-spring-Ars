package edu.nur.homework06.service;

import edu.nur.homework06.dao.GenreDao;
import edu.nur.homework06.model.Genre;
import edu.nur.homework06.service.validator.GenreInputValidator;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class GenreServiceImpl implements GenreService {

    private final GenreDao genreDao;

    public GenreServiceImpl(GenreDao genreDao) {
        this.genreDao = genreDao;
    }

    @Override
    public Genre save(String title) {
        GenreInputValidator.validateSaveInput(title);
        Genre genre = new Genre(title, new Date(), new Date());
        return genreDao.save(genre);
    }

    @Override
    public Genre update(long id, String title) {
        Genre genre = genreDao.getById(id);
        if (genre != null) {
            genre.setTitle(title);
        }
        return genreDao.save(genre);
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
