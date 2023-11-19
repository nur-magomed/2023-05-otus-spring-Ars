package edu.nur.homework07.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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