package edu.nur.homework05.util;

import edu.nur.homework05.model.Genre;
import edu.nur.homework05.service.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

import java.util.Date;
import java.util.List;

@ShellComponent
public class GenreShell {

    @Autowired
    private final GenreService genreService;

    public GenreShell(GenreService genreService) {
        this.genreService = genreService;
    }

    @ShellMethod(key = "genres", value = "List all genres: genres")
    public void genres() {
        List<Genre> genres = genreService.getAll();
        genres.forEach(System.out::println);
    }

    @ShellMethod(key = "genre new", value = "Add genre: genre new \"title of a genre\"")
    public void save(@ShellOption(value = "title", defaultValue = "") String title) {
        if (!title.isEmpty()) {
            Genre genre = new Genre(title, new Date(), new Date());
            genreService.save(genre);
        }
    }

    @ShellMethod(key = "genre update", value = "Update genre: genre update id \"Edited title\"")
    public void update(@ShellOption(value = "id", defaultValue = "-1") int id,
                       @ShellOption(value = "title", defaultValue = "") String title) {
        Genre genre = genreService.getById(id);
        if (genre != null) {
            genre.setTitle(title);
            genreService.update(genre);
        }
    }

    @ShellMethod(key = "genre print", value = "Print genre by Id: genre print id")
    public void printById(@ShellOption(value = "id", defaultValue = "-1") int id) {
        System.out.println(genreService.getById(id));
    }

    @ShellMethod(key = "genre delete", value = "Delete genre: genre delete id")
    public void delete(@ShellOption(value = "id", defaultValue = "-1") int id) {
        genreService.deleteById(id);
    }

    @ShellMethod(key = "genre count", value = "Count all genres")
    public int countAll() {
        return genreService.countAll();
    }

}
