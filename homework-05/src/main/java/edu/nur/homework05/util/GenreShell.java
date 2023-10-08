package edu.nur.homework05.util;

import edu.nur.homework05.service.GenreService;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

@ShellComponent
public class GenreShell {

    private final GenreService genreService;

    public GenreShell(GenreService genreService) {
        this.genreService = genreService;
    }

    @ShellMethod(key = "genres", value = "List all genres: genres")
    public void genres() {
        genreService.printAll();
    }

    @ShellMethod(key = "genre new", value = "Add genre: genre new \"title of a genre\"")
    public void save(@ShellOption(value = "title", defaultValue = "") String title) {
        genreService.save(title);
    }

    @ShellMethod(key = "genre update", value = "Update genre: genre update id \"Edited title\"")
    public void update(@ShellOption(value = "id", defaultValue = "-1") long id,
                       @ShellOption(value = "title", defaultValue = "") String title) {
        genreService.update(id, title);
    }

    @ShellMethod(key = "genre print", value = "Print genre by Id: genre print id")
    public void printById(@ShellOption(value = "id", defaultValue = "-1") long id) {
        genreService.printById(id);
    }

    @ShellMethod(key = "genre delete", value = "Delete genre: genre delete id")
    public void delete(@ShellOption(value = "id", defaultValue = "-1") long id) {
        genreService.deleteById(id);
    }

    @ShellMethod(key = "genre count", value = "Count all genres")
    public int countAll() {
        return genreService.countAll();
    }

}
