package edu.nur.homework06.service.converter;

import edu.nur.homework06.dto.CommentDto;
import edu.nur.homework06.model.Comment;

import java.util.List;
import java.util.stream.Collectors;

public class CommentConverter {

    public static CommentDto toCommentDto(Comment comment) {
        return new CommentDto(comment.getId(), BookConverter.toBookDto(comment.getBook()), comment.getMessage());
    }

    public static List<CommentDto> toCommentDtoList(List<Comment> comments) {
        return comments.stream().map(CommentConverter::toCommentDto).collect(Collectors.toList());
    }

    public static Comment toComment(CommentDto commentDto) {
        return new Comment(commentDto.getId(), BookConverter.toBook(commentDto.getBookDto()), commentDto.getMessage());
    }

    public static List<Comment> toCommentList(List<CommentDto> commentsDto) {
        return commentsDto.stream().map(CommentConverter::toComment).collect(Collectors.toList());
    }

}
