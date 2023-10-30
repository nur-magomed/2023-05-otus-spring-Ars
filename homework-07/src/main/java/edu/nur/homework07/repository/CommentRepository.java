package edu.nur.homework07.repository;

import edu.nur.homework07.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> findAllByBookId(long id);

}
