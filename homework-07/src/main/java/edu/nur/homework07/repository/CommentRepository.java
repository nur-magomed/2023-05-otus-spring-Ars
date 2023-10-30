package edu.nur.homework07.repository;

import edu.nur.homework07.model.Comment;

import java.util.List;
import java.util.Optional;

public interface CommentRepository {

    Comment save(Comment comment);

    Optional<Comment> findById(long id);

    List<Comment> findAllByBookId(long id);

    void deleteById(long id);

}