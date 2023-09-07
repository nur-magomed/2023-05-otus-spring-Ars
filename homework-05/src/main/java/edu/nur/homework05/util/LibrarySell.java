package edu.nur.homework05.util;

import edu.nur.homework05.model.Author;
import edu.nur.homework05.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

import java.util.List;

@ShellComponent
public class LibrarySell {

    @Autowired
    private final AuthorService authorService;

    public LibrarySell(AuthorService authorService) {
        this.authorService = authorService;
    }

    @ShellMethod(key = "hello", value = "Say hello")
    public String hello(@ShellOption(value = "name", defaultValue = "world") String name) {
        return "Hello, " + name;
    }

    @ShellMethod(key = "goodbye", value = "Say goodbye")
    public String goodbye() {
        return "Goodbye!";
    }

    @ShellMethod(key = "authors", value = "List all authors")
    public void authors() {
        List<Author> authors = authorService.getAll();
        authors.forEach(System.out::println);
    }


}
