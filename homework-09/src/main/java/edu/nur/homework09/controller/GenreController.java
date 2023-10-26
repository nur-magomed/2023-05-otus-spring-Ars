package edu.nur.homework09.controller;

import edu.nur.homework09.dto.GenreDto;
import edu.nur.homework09.exception.NotFoundException;
import edu.nur.homework09.model.Genre;
import edu.nur.homework09.repository.GenreRepository;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class GenreController {

    private final GenreRepository repository;

    public GenreController(GenreRepository repository) {
        this.repository = repository;
    }

    @GetMapping("genres")
    public String listGenres(Model model) {
        List<Genre> genres = repository.findAll();
        model.addAttribute("genres", genres);
        return "genres";
    }

    @GetMapping("/genres/edit")
    public String editPage(@RequestParam("id") long id, Model model) {
        Genre genre = new Genre();
        if (id != 0){
            genre = repository.findById(id).orElseThrow(NotFoundException::new);
        }
        model.addAttribute("genre", genre);
        return "genre_edit";
    }

    @PostMapping("/genres/edit")
    public String saveGenre(@Valid @ModelAttribute("genre") GenreDto genreDto,
                             BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "edit";
        }
        repository.save(genreDto.toModelObject());
        return "redirect:/genres";
    }
}
