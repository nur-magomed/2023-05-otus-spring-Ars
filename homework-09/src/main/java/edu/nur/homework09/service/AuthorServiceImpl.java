package edu.nur.homework09.service;


import edu.nur.homework09.model.Author;
import edu.nur.homework09.repository.AuthorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository repository;

    @Override
    public List<Author> findAll() {
        return repository.findAll();
    }

    @Override
    public Optional<Author> findById(long id) {
        return repository.findById(id);
    }

    @Override
    public Author save(Author author) {
        return repository.save(author);
    }

    @Override
    public void delete(Author author) {
        repository.delete(author);
    }

    @Override
    public void deleteById(long id) {
        repository.deleteById(id);
    }

}
