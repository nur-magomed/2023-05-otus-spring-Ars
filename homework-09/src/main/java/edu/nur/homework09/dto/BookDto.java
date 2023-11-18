package edu.nur.homework09.dto;

import edu.nur.homework09.model.Author;
import edu.nur.homework09.model.Book;
import edu.nur.homework09.model.Genre;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class BookDto {

    private long id;

    @NotBlank(message = "{title-field-should-not-be-blank}")
    @Size(min = 2, max = 30, message = "{title-field-should-has-expected-size}")
    private String title;

    @Valid
    @Size(min = 1, message = "{authors-field-should-not-be-empty}")
    @NotNull(message = "{authors-field-should-not-be-empty}")
    private Set<Author> authors;

    @Valid
    @NotNull(message = "{genre-field-should-not-be-null}")
    private Genre genre;

    public Book toModelObject() {
        return new Book(id, title, authors, genre);
    }

}
