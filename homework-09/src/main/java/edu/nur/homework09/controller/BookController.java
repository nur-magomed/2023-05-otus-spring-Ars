package edu.nur.homework09.controller;

import edu.nur.homework09.dto.BookDto;
import edu.nur.homework09.exception.NotFoundException;
import edu.nur.homework09.model.Author;
import edu.nur.homework09.model.Book;
import edu.nur.homework09.model.Genre;
import edu.nur.homework09.service.AuthorService;
import edu.nur.homework09.service.BookService;
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

import java.util.HashSet;
import java.util.List;

@RequiredArgsConstructor
@Controller
public class BookController {

    private final BookService bookService;

    private final GenreService genreService;

    private final AuthorService authorService;

    @GetMapping("books")
    public String listGenres(Model model) {
        List<Book> books = bookService.findAll();
        model.addAttribute("books", books);
        return "book_list";
    }

    @GetMapping("/book/edit")
    public String editPage(@RequestParam("id") long id, Model model) {
        Book book = new Book();
        book.setAuthors(new HashSet<>());
        if (id != 0) {
            book = bookService.findById(id).orElseThrow(NotFoundException::new);
        }
        List<Genre> genres = genreService.findAll();
        List<Author> authors = authorService.findAll();
        model.addAttribute("book", book);
        model.addAttribute("allGenres", genres);
        model.addAttribute("allAuthors", authors);
        return "book_edit";
    }

    @PostMapping("/book/edit")
    public String saveBook(@Valid @ModelAttribute("book") BookDto bookDto, BindingResult br, Model model) {
        if (br.hasErrors()) {
            if (bookDto.getAuthors() == null) {
                bookDto.setAuthors(new HashSet<>());
            }
            List<Genre> genres = genreService.findAll();
            List<Author> authors = authorService.findAll();
            model.addAttribute("allGenres", genres);
            model.addAttribute("allAuthors", authors);
            return "book_edit";
        }
        bookService.save(bookDto.toModelObject());
        return "redirect:/books";
    }

    @PostMapping("/book/delete/{id}")
    public String deleteBook(@PathVariable("id") long id) {
        bookService.deleteById(id);
        return "redirect:/books";
    }

}
