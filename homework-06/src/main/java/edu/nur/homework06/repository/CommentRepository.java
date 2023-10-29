package edu.nur.homework06.repository;

import edu.nur.homework06.model.Comment;

import java.util.List;
import java.util.Optional;

public interface CommentRepository {

    Comment save(Comment comment);

    Optional<Comment> findById(long id);

    List<Comment> findAllByBookId(long id);

    void deleteById(long id);

}
