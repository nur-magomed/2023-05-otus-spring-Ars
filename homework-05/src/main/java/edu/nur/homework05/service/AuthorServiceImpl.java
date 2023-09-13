package edu.nur.homework05.service;

import edu.nur.homework05.dao.AuthorDao;
import edu.nur.homework05.model.Author;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorServiceImpl implements AuthorService {

    private final AuthorDao authorDao;

    public AuthorServiceImpl(AuthorDao authorDao) {
        this.authorDao = authorDao;
    }

    @Override
    public Author save(Author author) {
        return authorDao.save(author);
    }

    @Override
    public Author update(Author author) {
        return authorDao.update(author);
    }

    @Override
    public Author getById(long id) {
        return authorDao.getById(id);
    }

    @Override
    public List<Author> getAll() {
        return authorDao.getAll();
    }

    @Override
    public void deleteById(long id) {
        authorDao.deleteById(id);
    }

    @Override
    public int countAll() {
        return authorDao.countAll();
    }

}
