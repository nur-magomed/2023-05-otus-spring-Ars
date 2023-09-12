package edu.nur.homework05.util;

import edu.nur.homework05.model.Author;
import edu.nur.homework05.model.Book;
import edu.nur.homework05.model.Genre;
import edu.nur.homework05.service.AuthorService;
import edu.nur.homework05.service.BookService;
import edu.nur.homework05.service.GenreService;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@ShellComponent
public class BookShell {

    private final BookService bookService;

    private final AuthorService authorService;

    private final GenreService genreService;

    public BookShell(BookService bookService, AuthorService authorService, GenreService genreService) {
        this.bookService = bookService;
        this.authorService = authorService;
        this.genreService = genreService;
    }

    @ShellMethod(key = "books", value = "List all books")
    public void books() {
        List<Book> books = bookService.getAll();
        books.forEach(System.out::println);
    }

    @ShellMethod(key = "book new",
            value = "Add book: book new \"title\" \"authorIds separated by comma\" \"genreIds separated by comma\"")
    public void save(@ShellOption(value = "title", defaultValue = "") String title,
                     @ShellOption(value = "authorIds", defaultValue = "") String authorIds,
                     @ShellOption(value = "genreIds", defaultValue = "") String genreIds) throws ParseException {
        if (!title.isEmpty() && !authorIds.isEmpty() && !genreIds.isEmpty()) {
            Book book = new Book(0, title, retrieveAuthors(authorIds), retrieveGenres(genreIds),
                    new Date(), new Date());
            bookService.save(book);
        }
    }

    @ShellMethod(key = "book update", value = "Update book: book update id \"title\" " +
            "\"authorIds separated by comma\" \"genreIds separated by comma\"")
    public void update(@ShellOption(value = "id", defaultValue = "-1") int id,
                       @ShellOption(value = "title", defaultValue = "") String title,
                       @ShellOption(value = "authorIds", defaultValue = "") String authorIds,
                       @ShellOption(value = "genreIds", defaultValue = "") String genreIds) throws ParseException  {
        Book book = bookService.getById(id);
        if (!title.isEmpty()) {
            book.setTitle(title);
        }
        if (!authorIds.isEmpty()) {
            book.setAuthors(retrieveAuthors(authorIds));
        }
        if (!genreIds.isEmpty()) {
            book.setGenres(retrieveGenres(genreIds));
        }
        bookService.update(book);
    }

    @ShellMethod(key = "book print", value = "Print book by Id: book print id")
    public void printById(@ShellOption(value = "id", defaultValue = "-1") int id) {
        System.out.println(bookService.getById(id));
    }

    @ShellMethod(key = "book delete", value = "Delete book: book delete id")
    public void delete(@ShellOption(value = "id", defaultValue = "-1") int id) {
        bookService.deleteById(id);
    }

    @ShellMethod(key = "book count", value = "Count all books")
    public int countAll() {
        return bookService.countAll();
    }

    private List<Author> retrieveAuthors(String ids) {
        List<Author> authorList = new ArrayList<>();
        String[] authorIdsArray = ids.split(",");
        List<Integer> authorIdsList = Arrays.stream(authorIdsArray).map(Integer::valueOf).toList();
        for (Integer authorId: authorIdsList) {
            Author author = authorService.getById(authorId);
            authorList.add(author);
        }
        return authorList;
    }

    private List<Genre> retrieveGenres(String ids) {
        List<Genre> genreList = new ArrayList<>();
        String[] genreIdsArray = ids.split(",");
        List<Integer> genreIdsList = Arrays.stream(genreIdsArray).map(Integer::valueOf).toList();
        for (Integer genreId: genreIdsList) {
            Genre genre = genreService.getById(genreId);
            genreList.add(genre);
        }
        return genreList;
    }
}
