package edu.nur.homework07.service;

import edu.nur.homework07.exception.AuthorInputException;
import edu.nur.homework07.model.Author;
import edu.nur.homework07.repository.AuthorRepository;
import edu.nur.homework07.service.validator.AuthorInputValidator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class AuthorServiceImpl implements AuthorService {

    private static final String FORMAT = "yyyy-MM-dd";

    private final AuthorRepository authorRepository;

    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Transactional
    @Override
    public Author save(String firstName, String lastName, String birthDate) {
        AuthorInputValidator.validateSaveInput(firstName, lastName, birthDate);
        Author author = new Author(0, firstName, lastName, parseBirthDate(birthDate), new Date(), new Date());
        return authorRepository.save(author);
    }

    @Transactional
    @Override
    public Author update(long id, String firstName, String lastName, String birthDate) {
        Author author = authorRepository.findById(id)
                .orElseThrow(() -> new AuthorInputException("Author not found with id: " + id));

        if (!firstName.isEmpty()) {
            author.setFirstName(firstName);
        }
        if (!lastName.isEmpty()) {
            author.setLastName(lastName);
        }
        if (!birthDate.isEmpty()) {
            author.setBirthDate(parseBirthDate(birthDate));
        }
        return authorRepository.save(author);
    }

    @Transactional(readOnly = true)
    @Override
    public Author getById(long id) {
        return authorRepository.findById(id)
                .orElseThrow(() -> new AuthorInputException("Author not found with id: " + id));
    }


    @Transactional(readOnly = true)
    @Override
    public List<Author> getAll() {
        return authorRepository.findAll();
    }

    @Transactional
    @Override
    public void deleteById(long id) {
        authorRepository.deleteById(id);
    }

    private Date parseBirthDate(String birthDate) {
        try {
            return new SimpleDateFormat(FORMAT).parse(birthDate);
        } catch (ParseException ignore) {
            throw new AuthorInputException("Wrong birthdate input format");
        }
    }
}
