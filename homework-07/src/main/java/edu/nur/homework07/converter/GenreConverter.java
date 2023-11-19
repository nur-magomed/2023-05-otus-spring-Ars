package edu.nur.homework07.converter;

import edu.nur.homework07.dto.GenreDto;
import edu.nur.homework07.model.Genre;

import java.util.List;
import java.util.stream.Collectors;

public class GenreConverter {

    public static GenreDto toGenreDto(Genre genre) {
        return new GenreDto(genre.getId(), genre.getTitle());
    }

    public static List<GenreDto> toGenreDtoList(List<Genre> genres) {
        return genres.stream().map(GenreConverter::toGenreDto).collect(Collectors.toList());
    }

    public static Genre toGenre(GenreDto genreDto) {
        return new Genre(genreDto.getId(), genreDto.getTitle());
    }



}
