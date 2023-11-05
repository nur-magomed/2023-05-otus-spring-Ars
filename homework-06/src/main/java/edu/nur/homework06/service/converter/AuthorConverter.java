package edu.nur.homework06.service.converter;

import edu.nur.homework06.dto.AuthorDto;
import edu.nur.homework06.model.Author;

import java.util.Set;
import java.util.stream.Collectors;

public class AuthorConverter {

    public static AuthorDto toAuthorDto(Author author) {
        return new AuthorDto(author.getId(), author.getFirstName(), author.getLastName(), author.getBirthDate());
    }

    public static Set<AuthorDto> toAuthorDtoSet(Set<Author> authors) {
        return authors.stream().map(AuthorConverter::toAuthorDto).collect(Collectors.toSet());
    }

    public static Author toAuthor(AuthorDto authorDto) {
        return new Author(authorDto.getId(), authorDto.getFirstName(), authorDto.getLastName(),
                authorDto.getBirthDate());
    }

    public static Set<Author> toAuthorSet(Set<AuthorDto> authorsDto) {
        return authorsDto.stream().map(AuthorConverter::toAuthor).collect(Collectors.toSet());
    }

}
