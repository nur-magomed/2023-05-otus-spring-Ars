package edu.nur.homework09.controller;

import edu.nur.homework09.dto.AuthorDto;
import edu.nur.homework09.exception.NotFoundException;
import edu.nur.homework09.model.Author;
import edu.nur.homework09.service.AuthorService;
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
public class AuthorController {

    private final AuthorService authorService;

    @GetMapping("authors")
    public String listAuthors(Model model) {
        List<Author> authors = authorService.findAll();
        model.addAttribute("authors", authors);
        return "author_list";
    }

    @GetMapping("/author/edit")
    public String editPage(@RequestParam("id") long id, Model model) {
        Author author = new Author();
        if (id != 0) {
            author = authorService.findById(id).orElseThrow(NotFoundException::new);
        }
        model.addAttribute("author", author);
        return "author_edit";
    }

    @PostMapping("/author/edit")
    public String saveAuthor(@Valid @ModelAttribute("author") AuthorDto authorDto, BindingResult br, Model model) {
        if (br.hasErrors()) {
            return "author_edit";
        }
        authorService.save(authorDto.toModelObject());
        return "redirect:/authors";
    }

    @PostMapping("/author/delete/{id}")
    public String deleteAuthor(@PathVariable("id") long id) {
        authorService.deleteById(id);
        return "redirect:/authors";
    }

}
