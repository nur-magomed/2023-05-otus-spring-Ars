package edu.nur.homework07.converter;

import edu.nur.homework07.dto.BookDto;
import edu.nur.homework07.model.Book;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class BookConverter {

    public static BookDto toBookDto(Book book) {
        return new BookDto(book.getId(), book.getTitle(), GenreConverter.toGenreDto(book.getGenre()),
                AuthorConverter.toAuthorDtoSet(book.getAuthors()),
                CommentConverter.toCommentDtoList(book.getComments()));
    }

    public static BookDto toBookDtoNoComments(Book book) {
        return new BookDto(book.getId(), book.getTitle(), GenreConverter.toGenreDto(book.getGenre()),
                AuthorConverter.toAuthorDtoSet(book.getAuthors()), new ArrayList<>());
    }

    public static List<BookDto> toBookDtoList(List<Book> books) {
        return books.stream().map(BookConverter::toBookDto).collect(Collectors.toList());
    }

    public static Book toBook(BookDto bookDto) {
        return new Book(bookDto.getId(), bookDto.getTitle(), GenreConverter.toGenre(bookDto.getGenreDto()),
                AuthorConverter.toAuthorSet(bookDto.getAuthorsDto()),
                CommentConverter.toCommentList(bookDto.getCommentsDto()));
    }

    public static List<BookDto> toBookDtoListNoComments(List<Book> books) {
        return books.stream().map(BookConverter::toBookDtoNoComments).collect(Collectors.toList());
    }
}
