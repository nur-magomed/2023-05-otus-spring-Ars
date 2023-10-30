package edu.nur.homework07.service;

import edu.nur.homework07.exception.GenreInputException;
import edu.nur.homework07.model.Genre;
import edu.nur.homework07.repository.GenreRepository;
import edu.nur.homework07.service.validator.GenreInputValidator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class GenreServiceImpl implements GenreService {

    private final GenreRepository genreRepository;

    public GenreServiceImpl(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }

    @Transactional
    @Override
    public Genre save(String title) {
        GenreInputValidator.validateSaveInput(title);
        Genre genre = new Genre(0, title, new Date(), new Date());
        return genreRepository.save(genre);
    }

    @Transactional
    @Override
    public Genre update(long id, String title) {
        Genre genre = genreRepository.findById(id)
                .orElseThrow(() -> new GenreInputException("Genre not found with id: " + id));
        genre.setTitle(title);
        return genreRepository.save(genre);
    }

    @Transactional(readOnly = true)
    @Override
    public Genre getById(long id) {
        return genreRepository.findById(id)
                .orElseThrow(() -> new GenreInputException("Genre not found with id: " + id));
    }

    @Transactional(readOnly = true)
    @Override
    public List<Genre> getAll() {
        return genreRepository.findAll();
    }

    @Transactional
    @Override
    public void deleteById(long id) {
        genreRepository.deleteById(id);
    }

}
