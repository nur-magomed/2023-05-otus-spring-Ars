package edu.nur.homework06.dto;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;

import java.util.List;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class BookDto {

    private long id;

    private String title;

    private GenreDto genreDto;

    private Set<AuthorDto> authorsDto;

    private List<CommentDto> commentsDto;

}