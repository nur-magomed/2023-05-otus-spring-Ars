package edu.nur.homework05.util;

import edu.nur.homework05.model.Author;
import edu.nur.homework05.model.Book;
import edu.nur.homework05.model.Genre;
import edu.nur.homework05.service.AuthorService;
import edu.nur.homework05.service.BookService;
import edu.nur.homework05.service.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@ShellComponent
public class BookShell {

    @Autowired
    private final BookService bookService;

    @Autowired
    private final AuthorService authorService;

    @Autowired
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

    @ShellMethod(key = "book new", value = "Add book: book new \"title\" \"authorIds\" \"genreIds\"")
    public void save(@ShellOption(value = "title", defaultValue = "") String title,
                     @ShellOption(value = "authorIds", defaultValue = "") String authorIds,
                     @ShellOption(value = "genreIds", defaultValue = "") String genreIds) throws ParseException {
        if (!title.isEmpty() && !authorIds.isEmpty() && !genreIds.isEmpty()) {
            String[] authorIdsArray = authorIds.split(",");
            List<Integer> authorIdsList = Arrays.stream(authorIdsArray).map(Integer::valueOf).toList();
            List<Author> authorList = new ArrayList<>();
            for (Integer authorId: authorIdsList) {
                Author author = authorService.getById(authorId);
                authorList.add(author);
            }
            String[] genreIdsArray = genreIds.split(",");
            List<Integer> genreIdsList = Arrays.stream(genreIdsArray).map(Integer::valueOf).toList();
            List<Genre> genreList = new ArrayList<>();
            for (Integer genreId: genreIdsList) {
                Genre genre = genreService.getById(genreId);
                genreList.add(genre);
            }

            Book book = new Book(0, title, authorList, genreList, new Date(), new Date());
            bookService.save(book);
        }
    }

    @ShellMethod(key = "author update", value = "Update author: author update id \"First name\" \"Last name\" \"yyyy-MM-dd\"")
    public void update(@ShellOption(value = "id", defaultValue = "-1") int id,
                       @ShellOption(value = "firstName", defaultValue = "") String firstName,
                       @ShellOption(value = "lastName", defaultValue = "") String lastName,
                       @ShellOption(value = "birthDate", defaultValue = "") String birthDate) throws ParseException {
        Author author = authorService.getById(id);
        if (!firstName.isEmpty()) {
            author.setFirstName(firstName);
        }

        if (!lastName.isEmpty()) {
            author.setLastName(lastName);
        }

        if (!birthDate.isEmpty()) {

        }

        authorService.update(author);
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
}
