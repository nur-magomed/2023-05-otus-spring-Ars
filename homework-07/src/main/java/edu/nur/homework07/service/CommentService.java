package edu.nur.homework07.service;

import edu.nur.homework07.model.Comment;

import java.util.List;

public interface CommentService {

    Comment save(String message, long bookId);

    Comment update(long id, String message);

    Comment getById(long id);

    List<Comment> findAllByBookId(long bookId);

    void deleteById(long id);

}
