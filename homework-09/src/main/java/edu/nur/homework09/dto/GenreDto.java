package edu.nur.homework09.dto;

import edu.nur.homework09.model.Genre;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class GenreDto {

    private long id;

    private String title;

    public Genre toModelObject() {
        return new Genre(id, title);
    }

    public static GenreDto fromModelObject(Genre genre) {
        return new GenreDto(genre.getId(), genre.getTitle());
    }

}