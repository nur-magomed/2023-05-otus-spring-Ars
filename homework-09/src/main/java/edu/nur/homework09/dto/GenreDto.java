package edu.nur.homework09.dto;

import edu.nur.homework09.model.Genre;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class GenreDto {

    private long id;

    private String title;

    private LocalDateTime createdDate;

    private LocalDateTime modifiedDate;

    public Genre toModelObject() {
        return new Genre(id, title, createdDate, modifiedDate);
    }

    public static GenreDto fromModelObject(Genre genre) {
        return new GenreDto(genre.getId(), genre.getTitle(), genre.getCreatedDate(), genre.getModifiedDate());
    }

}