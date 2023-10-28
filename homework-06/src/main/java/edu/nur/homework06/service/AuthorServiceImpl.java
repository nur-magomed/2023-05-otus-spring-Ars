package edu.nur.homework06.service;

import edu.nur.homework06.dao.AuthorDao;
import edu.nur.homework06.exception.AuthorInputException;
import edu.nur.homework06.model.Author;
import edu.nur.homework06.service.validator.AuthorInputValidator;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class AuthorServiceImpl implements AuthorService {

    private static final String FORMAT = "yyyy-MM-dd";

    private final AuthorDao authorDao;

    public AuthorServiceImpl(AuthorDao authorDao) {
        this.authorDao = authorDao;
    }

    @Override
    public Author save(String firstName, String lastName, String birthDate) {
        AuthorInputValidator.validateSaveInput(firstName, lastName, birthDate);
        Author author = new Author(0, firstName, lastName, parseBirthDate(birthDate), new Date(), new Date());
        return authorDao.save(author);
    }

    @Override
    public Author update(long id, String firstName, String lastName, String birthDate) {
        Author author = authorDao.getById(id);
        if (!firstName.isEmpty()) {
            author.setFirstName(firstName);
        }
        if (!lastName.isEmpty()) {
            author.setLastName(lastName);
        }
        if (!birthDate.isEmpty()) {
            author.setBirthDate(parseBirthDate(birthDate));
        }
        return authorDao.save(author);
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

    private Date parseBirthDate(String birthDate) {
        try {
            return new SimpleDateFormat(FORMAT).parse(birthDate);
        } catch (ParseException ignore) {
            throw new AuthorInputException("Wrong birthdate input format");
        }
    }
}
