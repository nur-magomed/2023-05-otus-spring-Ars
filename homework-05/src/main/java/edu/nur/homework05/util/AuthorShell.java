package edu.nur.homework05.util;

import edu.nur.homework05.model.Author;
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

    @ShellMethod(value = "All authors", key = "authors")
    public void authors() {
        authorService.printAll();
    }

    @ShellMethod(value = "Add author: author new \"First name\" \"Last name\" \"yyyy-MM-dd\"",
                 key = "author new")
    public void save(@ShellOption(value = "firstName", defaultValue = "") String firstName,
                     @ShellOption(value = "lastName", defaultValue = "") String lastName,
                     @ShellOption(value = "birthDate", defaultValue = "") String birthDate) {
        authorService.save(firstName, lastName, birthDate);
    }

    @ShellMethod(value = "Update author: author update id \"First name\" \"Last name\" \"yyyy-MM-dd\"",
                 key = "author update")
    public void update(@ShellOption(value = "id", defaultValue = "-1") long id,
                       @ShellOption(value = "firstName", defaultValue = "") String firstName,
                       @ShellOption(value = "lastName", defaultValue = "") String lastName,
                       @ShellOption(value = "birthDate", defaultValue = "") String birthDate) throws ParseException {
        authorService.update(id, firstName, lastName, birthDate);
    }

    @ShellMethod(value = "Print author by Id: author print id", key = "author print")
    public void printById(@ShellOption(value = "id", defaultValue = "-1") long id) {
        authorService.printById(id);
    }

    @ShellMethod(value = "Delete author: author delete id", key = "author delete")
    public void delete(@ShellOption(value = "id", defaultValue = "-1") long id) {
        authorService.deleteById(id);
    }

    @ShellMethod(value = "Count all authors", key = "author count")
    public int countAll() {
        return authorService.countAll();
    }

}
