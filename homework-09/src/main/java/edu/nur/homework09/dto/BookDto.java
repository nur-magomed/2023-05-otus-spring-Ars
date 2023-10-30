package edu.nur.homework09.dto;

import edu.nur.homework09.model.Author;
import edu.nur.homework09.model.Book;
import edu.nur.homework09.model.Genre;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class BookDto {

    private long id;

    private String title;

    private Set<Author> authors;

    private Genre genre;

    public Book toModelObject() {
        return new Book(id, title, authors, genre);
    }

    public static BookDto fromModelObject(Book book) {
        return new BookDto(book.getId(), book.getTitle(), book.getAuthors(), book.getGenre());
    }

}
