package edu.nur.homework09.controller;

import edu.nur.homework09.dto.GenreDto;
import edu.nur.homework09.exception.NotFoundException;
import edu.nur.homework09.model.Genre;
import edu.nur.homework09.service.GenreService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class GenreController {

    private final GenreService genreService;

    @GetMapping("genres")
    public String listGenres(Model model) {
        List<Genre> genres = genreService.findAll();
        model.addAttribute("genres", genres);
        return "genre_list";
    }

    @GetMapping("/genre/edit")
    public String editPage(@RequestParam("id") long id, Model model) {
        Genre genre = new Genre();
        if (id != 0) {
            genre = genreService.findById(id).orElseThrow(NotFoundException::new);
        }
        model.addAttribute("genre", genre);
        return "genre_edit";
    }

    @PostMapping("/genre/edit")
    public String saveGenre(@Valid @ModelAttribute("genre") GenreDto genreDto, BindingResult br, Model model) {
        if (br.hasErrors()) {
            return "genre_edit";
        }
        genreService.save(genreDto.toModelObject());
        return "redirect:/genres";
    }

    @PostMapping("/genre/delete/{id}")
    public String deleteGenre(@PathVariable("id") long id) {
        genreService.deleteById(id);
        return "redirect:/genres";
    }

}
