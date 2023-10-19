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

    @ShellMethod(value = "List all genres: genres", key = "genres")
    public void genres() {
        genreService.printAll();
    }

    @ShellMethod(value = "Add genre: genre new \"title of a genre\"", key = "genre new")
    public void save(@ShellOption(value = "title", defaultValue = "") String title) {
        genreService.save(title);
    }

    @ShellMethod(value = "Update genre: genre update id \"Edited title\"", key = "genre update")
    public void update(@ShellOption(value = "id", defaultValue = "-1") long id,
                       @ShellOption(value = "title", defaultValue = "") String title) {
        genreService.update(id, title);
    }

    @ShellMethod(value = "Print genre by Id: genre print id", key = "genre print")
    public void printById(@ShellOption(value = "id", defaultValue = "-1") long id) {
        genreService.printById(id);
    }

    @ShellMethod(value = "Delete genre: genre delete id", key = "genre delete")
    public void delete(@ShellOption(value = "id", defaultValue = "-1") long id) {
        genreService.deleteById(id);
    }

    @ShellMethod(value = "Count all genres", key = "genre count")
    public int countAll() {
        return genreService.countAll();
    }

}
