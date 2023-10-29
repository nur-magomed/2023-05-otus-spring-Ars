package edu.nur.homework09.dto;

import edu.nur.homework09.model.Author;
import edu.nur.homework09.model.Book;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;
import java.util.stream.Collectors;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class BookDto {

    private long id;

    private String title;

    private Set<AuthorDto> authors;

    private GenreDto genre;

    public Book toModelObject() {
        Set<Author> authorModels = authors.stream().map(AuthorDto::toModelObject).collect(Collectors.toSet());
        return new Book(id, title, authorModels, genre.toModelObject());
    }

    public static BookDto fromModelObject(Book book) {
        Set<AuthorDto> authorDtos = book.getAuthors().stream()
                .map(AuthorDto::fromModelObject).collect(Collectors.toSet());
        return new BookDto(book.getId(), book.getTitle(), authorDtos, GenreDto.fromModelObject(book.getGenre()));
    }

}
