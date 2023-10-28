package edu.nur.homework05.service;

import edu.nur.homework05.dao.BookDao;
import edu.nur.homework05.model.Author;
import edu.nur.homework05.model.Book;
import edu.nur.homework05.model.Genre;
import edu.nur.homework05.service.validator.BookInputValidator;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.HashSet;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    private final BookDao bookDao;

    private final AuthorService authorService;

    private final GenreService genreService;

    public BookServiceImpl(BookDao bookDao, AuthorService authorService, GenreService genreService) {
        this.bookDao = bookDao;
        this.authorService = authorService;
        this.genreService = genreService;
    }

    @Override
    public Book save(String title, String authorIds, String genreId) {
        BookInputValidator.validateSaveInput(title, authorIds, genreId);
        Book book = new Book(0, title, retrieveAuthors(authorIds), retrieveGenre(genreId), new Date(), new Date());
        return bookDao.save(book);
    }

    @Override
    public Book update(long id, String title, String authorIds, String genreId) {
        Book book = bookDao.getById(id);
        if (!title.isEmpty()) {
            book.setTitle(title);
        }
        if (!authorIds.isEmpty()) {
            book.setAuthors(retrieveAuthors(authorIds));
        }
        if (!genreId.isEmpty()) {
            book.setGenre(retrieveGenre(genreId));
        }
        return bookDao.save(book);
    }

    @Override
    public Book getById(long id) {
        return bookDao.getById(id);
    }

    @Override
    public List<Book> getAll() {
        return bookDao.getAll();
    }

    @Override
    public void printById(long id) {
        Book book = bookDao.getById(id);
        System.out.println("Book title: " + book.getTitle());
    }

    @Override
    public void printAll() {
        List<Book> books = bookDao.getAll();
        for (Book b: books) {
            System.out.println("Book title: " + b.getTitle());
        }
    }

    @Override
    public void deleteById(long id) {
        bookDao.deleteById(id);
    }

    @Override
    public int countAll() {
        return bookDao.countAll();
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
