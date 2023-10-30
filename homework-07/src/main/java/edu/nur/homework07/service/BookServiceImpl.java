package edu.nur.homework07.service;

import edu.nur.homework07.exception.BookInputException;
import edu.nur.homework07.repository.BookRepository;
import edu.nur.homework07.model.Author;
import edu.nur.homework07.model.Book;
import edu.nur.homework07.model.Genre;
import edu.nur.homework07.service.validator.BookInputValidator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;
import java.util.HashSet;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Collections;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    private final AuthorService authorService;

    private final GenreService genreService;

    public BookServiceImpl(BookRepository bookRepository, AuthorService authorService, GenreService genreService) {
        this.bookRepository = bookRepository;
        this.authorService = authorService;
        this.genreService = genreService;
    }

    @Transactional
    @Override
    public Book save(String title, String authorIds, String genreId) {
        BookInputValidator.validateSaveInput(title, authorIds, genreId);
        Book book = new Book(0, title, retrieveGenre(genreId), retrieveAuthors(authorIds), Collections.emptyList(),
                new Date(), new Date());
        return bookRepository.save(book);
    }

    @Transactional
    @Override
    public Book update(long id, String title, String authorIds, String genreId) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new BookInputException("Book not found with id: " + id));
        if (!title.isEmpty()) {
            book.setTitle(title);
        }
        if (!authorIds.isEmpty()) {
            book.setAuthors(retrieveAuthors(authorIds));
        }
        if (!genreId.isEmpty()) {
            book.setGenre(retrieveGenre(genreId));
        }
        return bookRepository.save(book);
    }

    @Transactional(readOnly = true)
    @Override
    public Book getById(long id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> new BookInputException("Book not found with id: " + id));
    }

    @Transactional(readOnly = true)
    @Override
    public List<Book> getAll() {
        return bookRepository.findAll();
    }

    @Transactional
    @Override
    public void deleteById(long id) {
        bookRepository.deleteById(id);
    }


    private Set<Author> retrieveAuthors(String ids) {
        Set<Author> authorList = new HashSet<>();
        String[] authorIdsArray = ids.split(",");
        List<Long> authorIdsList = Arrays.stream(authorIdsArray).map(Long::parseLong).toList();
        for (Long authorId: authorIdsList) {
            Author author = authorService.getById(authorId);
            authorList.add(author);
        }
        return authorList;
    }

    private Genre retrieveGenre(String id) {
        return genreService.getById(Long.parseLong(id));
    }
}
