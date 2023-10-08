package edu.nur.homework05.util;

import edu.nur.homework05.service.AuthorService;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

import java.text.ParseException;

@ShellComponent
public class AuthorShell {

    private final AuthorService authorService;

    public AuthorShell(AuthorService authorService) {
        this.authorService = authorService;
    }

    @ShellMethod(key = "authors", value = "List all authors")
    public void authors() {
        authorService.printAll();
    }

    @ShellMethod(key = "author new", value = "Add author: author new \"First name\" \"Last name\" \"yyyy-MM-dd\"")
    public void save(@ShellOption(value = "firstName", defaultValue = "") String firstName,
                     @ShellOption(value = "lastName", defaultValue = "") String lastName,
                     @ShellOption(value = "birthDate", defaultValue = "") String birthDate) {
        authorService.save(firstName, lastName, birthDate);
    }

    @ShellMethod(key = "author update",
            value = "Update author: author update id \"First name\" \"Last name\" \"yyyy-MM-dd\"")
    public void update(@ShellOption(value = "id", defaultValue = "-1") long id,
                       @ShellOption(value = "firstName", defaultValue = "") String firstName,
                       @ShellOption(value = "lastName", defaultValue = "") String lastName,
                       @ShellOption(value = "birthDate", defaultValue = "") String birthDate) throws ParseException {
        authorService.update(id, firstName, lastName, birthDate);
    }

    @ShellMethod(key = "author print", value = "Print author by Id: author print id")
    public void printById(@ShellOption(value = "id", defaultValue = "-1") long id) {
        authorService.printById(id);
    }

    @ShellMethod(key = "author delete", value = "Delete author: author delete id")
    public void delete(@ShellOption(value = "id", defaultValue = "-1") long id) {
        authorService.deleteById(id);
    }

    @ShellMethod(key = "author count", value = "Count all authors")
    public int countAll() {
        return authorService.countAll();
    }

}
