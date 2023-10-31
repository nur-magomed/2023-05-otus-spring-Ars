package edu.nur.homework09.dto;

import edu.nur.homework09.model.Genre;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class GenreDto {

    private long id;

    @NotBlank(message = "{title-field-should-not-be-blank}")
    @Size(min = 2, max = 30, message = "{title-field-should-has-expected-size}")
    private String title;

    public Genre toModelObject() {
        return new Genre(id, title);
    }

    public static GenreDto fromModelObject(Genre genre) {
        return new GenreDto(genre.getId(), genre.getTitle());
    }

}