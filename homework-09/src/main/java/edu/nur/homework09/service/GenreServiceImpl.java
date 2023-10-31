package edu.nur.homework09.service;

import edu.nur.homework09.model.Genre;
import edu.nur.homework09.repository.GenreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class GenreServiceImpl implements GenreService {

    private final GenreRepository repository;

    @Override
    public List<Genre> findAll() {
        return repository.findAll();
    }

    @Override
    public Optional<Genre> findById(long id) {
        return repository.findById(id);
    }

    @Override
    public Genre save(Genre genre) {
        return repository.save(genre);
    }

    @Override
    public void delete(Genre genre) {
        repository.delete(genre);
    }

    @Override
    public void deleteById(long id) {
        repository.deleteById(id);
    }

}
