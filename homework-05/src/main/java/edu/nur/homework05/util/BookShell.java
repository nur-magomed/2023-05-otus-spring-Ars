package edu.nur.homework05.util;

import edu.nur.homework05.model.Book;
import edu.nur.homework05.service.BookService;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

import java.text.ParseException;
import java.util.List;

@ShellComponent
public class BookShell {

    private final BookService bookService;

    public BookShell(BookService bookService) {
        this.bookService = bookService;
    }

    @ShellMethod(key = "books", value = "List all books")
    public void books() {
        List<Book> books = bookService.getAll();
        books.forEach(System.out::println);
    }

    @ShellMethod(key = "book new",
            value = "Add book: book new \"title\" \"authorIds separated by comma\" \"genreId\"")
    public void save(@ShellOption(value = "title", defaultValue = "") String title,
                     @ShellOption(value = "authorIds", defaultValue = "") String authorIds,
                     @ShellOption(value = "genreId", defaultValue = "") String genreId) throws ParseException {
        bookService.save(title, authorIds, genreId);
    }

    @ShellMethod(key = "book update", value = "Update book: book update id \"title\" " +
            "\"authorIds separated by comma\" \"genreId\"")
    public void update(@ShellOption(value = "id", defaultValue = "-1") long id,
                       @ShellOption(value = "title", defaultValue = "") String title,
                       @ShellOption(value = "authorIds", defaultValue = "") String authorIds,
                       @ShellOption(value = "genreId", defaultValue = "") String genreId) throws ParseException  {
        bookService.update(id, title, authorIds, genreId);
    }

    @ShellMethod(key = "book print", value = "Print book by Id: book print id")
    public void printById(@ShellOption(value = "id", defaultValue = "-1") long id) {
        bookService.printById(id);
    }

    @ShellMethod(key = "book delete", value = "Delete book: book delete id")
    public void delete(@ShellOption(value = "id", defaultValue = "-1") long id) {
        bookService.deleteById(id);
    }

    @ShellMethod(key = "book count", value = "Count all books")
    public int countAll() {
        return bookService.countAll();
    }
}
