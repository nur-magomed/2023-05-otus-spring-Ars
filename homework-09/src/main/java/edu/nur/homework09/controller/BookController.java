package edu.nur.homework09.controller;

import edu.nur.homework09.dto.BookDto;
import edu.nur.homework09.exception.NotFoundException;
import edu.nur.homework09.model.Author;
import edu.nur.homework09.model.Book;
import edu.nur.homework09.model.Genre;
import edu.nur.homework09.repository.AuthorRepository;
import edu.nur.homework09.repository.BookRepository;
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
public class BookController {

    private final BookRepository bookRepository;
    private final GenreRepository genreRepository;

    private final AuthorRepository authorRepository;

    public BookController(BookRepository bookRepository, GenreRepository genreRepository,
                          AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.genreRepository = genreRepository;
        this.authorRepository = authorRepository;
    }

    @GetMapping("books")
    public String listGenres(Model model) {
        List<Book> books = bookRepository.findAll();
        model.addAttribute("books", books);
        return "book_list";
    }

    @GetMapping("/book/edit")
    public String editPage(@RequestParam("id") long id, Model model) {
        Book book = new Book();
        if (id != 0) {
            book = bookRepository.findById(id).orElseThrow(NotFoundException::new);
        }
        List<Genre> genres = genreRepository.findAll();
        List<Author> authors = authorRepository.findAll();
        model.addAttribute("book", book);
        model.addAttribute("genres", genres);
        model.addAttribute("authors", authors);
        return "book_edit";
    }

    @PostMapping("/book/edit")
    public String saveGenre(@Valid @ModelAttribute("book") BookDto bookDto, BindingResult br, Model model) {
        if (br.hasErrors()) {
            return "book_edit";
        }
        bookRepository.save(bookDto.toModelObject());
        return "redirect:/books";
    }

}