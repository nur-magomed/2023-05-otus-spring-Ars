package edu.nur.homework05.util;

import edu.nur.homework05.model.Author;
import edu.nur.homework05.service.AuthorService;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@ShellComponent
public class AuthorShell {

    private final String format = "yyyy-MM-dd";

    private final AuthorService authorService;

    public AuthorShell(AuthorService authorService) {
        this.authorService = authorService;
    }

    @ShellMethod(key = "authors", value = "List all authors")
    public void authors() {
        List<Author> authors = authorService.getAll();
        authors.forEach(System.out::println);
    }

    @ShellMethod(key = "author new", value = "Add author: author new \"First name\" \"Last name\" \"yyyy-MM-dd\"")
    public void save(@ShellOption(value = "firstName", defaultValue = "") String firstName,
                     @ShellOption(value = "lastName", defaultValue = "") String lastName,
                     @ShellOption(value = "birthDate", defaultValue = "") String birthDate) throws ParseException {
        if (!firstName.isEmpty() && !lastName.isEmpty() && !birthDate.isEmpty()) {
            Date bDate = new SimpleDateFormat(format).parse(birthDate);
            Author genre = new Author(0, firstName, lastName, bDate, new Date(), new Date());
            authorService.insert(genre);
        }
    }

    @ShellMethod(key = "author update",
            value = "Update author: author update id \"First name\" \"Last name\" \"yyyy-MM-dd\"")
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
            Date bDate = new SimpleDateFormat(format).parse(birthDate);
            author.setBirthDate(bDate);
        }

        authorService.update(author);
    }

    @ShellMethod(key = "author print", value = "Print author by Id: author print id")
    public void printById(@ShellOption(value = "id", defaultValue = "-1") int id) {
        System.out.println(authorService.getById(id));
    }

    @ShellMethod(key = "author delete", value = "Delete author: author delete id")
    public void delete(@ShellOption(value = "id", defaultValue = "-1") int id) {
        authorService.deleteById(id);
    }

    @ShellMethod(key = "author count", value = "Count all authors")
    public int countAll() {
        return authorService.countAll();
    }

}
