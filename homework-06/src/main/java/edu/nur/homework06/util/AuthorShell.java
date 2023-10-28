package edu.nur.homework06.util;

import edu.nur.homework06.model.Author;
import edu.nur.homework06.service.AuthorService;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

import java.text.ParseException;
import java.util.List;

@ShellComponent
public class AuthorShell {

    private final AuthorService authorService;

    public AuthorShell(AuthorService authorService) {
        this.authorService = authorService;
    }

    @ShellMethod(value = "All authors", key = "authors")
    public String authors() {
        List<Author> authors = authorService.getAll();
        StringBuilder sb = new StringBuilder();
        authors.forEach(a -> sb.append(prepareView(a)).append("\n"));
        return sb.toString();
    }

    @ShellMethod(value = "Add author: author new \"First name\" \"Last name\" \"yyyy-MM-dd\"",
                 key = "author new")
    public String save(@ShellOption(value = "firstName", defaultValue = "") String firstName,
                     @ShellOption(value = "lastName", defaultValue = "") String lastName,
                     @ShellOption(value = "birthDate", defaultValue = "") String birthDate) {
        Author saved = authorService.save(firstName, lastName, birthDate);
        return prepareView(saved);
    }

    @ShellMethod(value = "Update author: author update id \"First name\" \"Last name\" \"yyyy-MM-dd\"",
                 key = "author update")
    public String update(@ShellOption(value = "id", defaultValue = "-1") long id,
                       @ShellOption(value = "firstName", defaultValue = "") String firstName,
                       @ShellOption(value = "lastName", defaultValue = "") String lastName,
                       @ShellOption(value = "birthDate", defaultValue = "") String birthDate) throws ParseException {
        Author updated = authorService.update(id, firstName, lastName, birthDate);
        return prepareView(updated);
    }

    @ShellMethod(value = "Get author by Id: author get id", key = "author get")
    public String getById(@ShellOption(value = "id", defaultValue = "-1") long id) {
        Author author = authorService.getById(id);
        return prepareView(author);
    }

    @ShellMethod(value = "Delete author: author delete id", key = "author delete")
    public String delete(@ShellOption(value = "id", defaultValue = "-1") long id) {
        authorService.deleteById(id);
        return String.format("Author deleted successfully id:%d", id);
    }

    @ShellMethod(value = "Count all authors", key = "author count")
    public int countAll() {
        return authorService.countAll();
    }

    private String prepareView(Author author) {
        return String.format("Author id:%d, lastName:%s, firstName:%s",
                author.getId(), author.getLastName(), author.getFirstName());
    }
}
