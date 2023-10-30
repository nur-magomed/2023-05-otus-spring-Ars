package edu.nur.homework06.util;

import edu.nur.homework06.model.Book;
import edu.nur.homework06.service.BookService;
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

    @ShellMethod(value = "List all books", key = "books")
    public String books() {
        List<Book> books = bookService.getAll();
        StringBuilder sb = new StringBuilder();
        books.forEach(b -> sb.append(prepareView(b)).append("\n"));
        return sb.toString();
    }

    @ShellMethod(value = "Add book: book new \"title\" \"authorIds separated by comma\" \"genreId\"",
                 key = "book new")
    public String save(@ShellOption(value = "title", defaultValue = "") String title,
                     @ShellOption(value = "authorIds", defaultValue = "") String authorIds,
                     @ShellOption(value = "genreId", defaultValue = "") String genreId) throws ParseException {
        Book saved = bookService.save(title, authorIds, genreId);
        return "Book saved successfully." + prepareView(saved);
    }

    @ShellMethod(value = "Update book: book update id \"title\" \"authorIds separated by comma\" \"genreId\"",
                 key = "book update")
    public String update(@ShellOption(value = "id", defaultValue = "-1") long id,
                       @ShellOption(value = "title", defaultValue = "") String title,
                       @ShellOption(value = "authorIds", defaultValue = "") String authorIds,
                       @ShellOption(value = "genreId", defaultValue = "") String genreId) throws ParseException  {
        Book updated = bookService.update(id, title, authorIds, genreId);
        return "Book updated successfully. " + prepareView(updated);
    }

    @ShellMethod(value = "Get book by Id: book get id", key = "book get")
    public String getById(@ShellOption(value = "id", defaultValue = "-1") long id) {
        Book book = bookService.getById(id);
        return prepareView(book);
    }

    @ShellMethod(value = "Delete book: book delete id", key = "book delete")
    public String delete(@ShellOption(value = "id", defaultValue = "-1") long id) {
        bookService.deleteById(id);
        return String.format("Book deleted successfully id:%d", id);
    }

    private String prepareView(Book book) {
        StringBuilder sb = new StringBuilder();
        book.getAuthors().forEach(a -> sb.append(a.getLastName()).append(" ").append(a.getFirstName()).append(", "));
        String authors = sb.toString();
        return String.format("Book id:%d, title:%s, genre:%s, authors:%s",
                book.getId(), book.getTitle(), book.getGenre().getTitle(), authors);
    }
}
