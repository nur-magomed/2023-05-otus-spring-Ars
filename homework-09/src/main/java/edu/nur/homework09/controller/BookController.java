package edu.nur.homework09.controller;

import edu.nur.homework09.model.Book;
import edu.nur.homework09.repository.BookRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class BookController {

    private final BookRepository bookRepository;

    public BookController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @GetMapping("books")
    public String listGenres(Model model) {
        List<Book> books = bookRepository.findAll();
        model.addAttribute("books", books);
        return "book_list";
    }
}
