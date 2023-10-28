package edu.nur.homework05.util;

import edu.nur.homework05.model.Genre;
import edu.nur.homework05.service.GenreService;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

import java.util.List;

@ShellComponent
public class GenreShell {

    private final GenreService genreService;

    public GenreShell(GenreService genreService) {
        this.genreService = genreService;
    }

    @ShellMethod(value = "List all genres: genres", key = "genres")
    public String genres() {
        List<Genre> genres = genreService.getAll();
        StringBuilder sb = new StringBuilder();
        genres.forEach(g -> sb.append(prepareView(g)).append("\n"));
        return sb.toString();
    }

    @ShellMethod(value = "Add genre: genre new \"title of a genre\"", key = "genre new")
    public String save(@ShellOption(value = "title", defaultValue = "") String title) {
        Genre saved = genreService.save(title);
        return "Genre saved successfully." + prepareView(saved);
    }

    @ShellMethod(value = "Update genre: genre update id \"Edited title\"", key = "genre update")
    public String update(@ShellOption(value = "id", defaultValue = "-1") long id,
                       @ShellOption(value = "title", defaultValue = "") String title) {
        Genre updated = genreService.update(id, title);
        return "Genre updated successfully. " + prepareView(updated);
    }

    @ShellMethod(value = "Get genre by Id: genre get id", key = "genre get")
    public String getById(@ShellOption(value = "id", defaultValue = "-1") long id) {
        Genre genre = genreService.getById(id);
        return prepareView(genre);
    }

    @ShellMethod(value = "Delete genre: genre delete id", key = "genre delete")
    public String delete(@ShellOption(value = "id", defaultValue = "-1") long id) {
        genreService.deleteById(id);
        return String.format("Genre deleted successfully id:%d", id);
    }

    @ShellMethod(value = "Count all genres", key = "genre count")
    public int countAll() {
        return genreService.countAll();
    }

    private String prepareView(Genre genre) {
        return String.format("Genre id: %d title: %s", genre.getId(), genre.getTitle());
    }
}
