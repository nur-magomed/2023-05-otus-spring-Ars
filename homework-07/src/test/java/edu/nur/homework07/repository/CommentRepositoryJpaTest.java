package edu.nur.homework07.repository;


import edu.nur.homework07.model.Comment;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Comment JPA repository should ")
@DataJpaTest
@Import(CommentRepositoryJpa.class)
public class CommentRepositoryJpaTest {

    private static final int EXISTING_COMMENT_ID = 1;

    private static final int EXISTING_BOOK_ID = 1;

    private static final int EXPECTED_BOOK_1_COMMENTS_COUNT = 2;

    @Autowired
    private CommentRepositoryJpa repositoryJpa;

    @Autowired
    private TestEntityManager em;

    @DisplayName("save new comment")
    @Test
    void saveTest() {
        Comment comment = new Comment(0, 1, "Test comment");
        repositoryJpa.save(comment);
        assertThat(comment.getId()).isGreaterThan(0);

        Comment actualComment = em.find(Comment.class, comment.getId());
        assertThat(comment).usingRecursiveComparison().isEqualTo(actualComment);
    }

    @DisplayName("update existing comment")
    @Test
    void updateTest() {
        Optional<Comment> optionalComment = repositoryJpa.findById(EXISTING_COMMENT_ID);
        Comment comment = optionalComment.get();
        comment.setMessage("updated comment message");
        repositoryJpa.save(comment);
        Comment actualComment = em.find(Comment.class, EXISTING_COMMENT_ID);
        assertThat(comment).usingRecursiveComparison().isEqualTo(actualComment);
    }

    @DisplayName("find comment by ID")
    @Test
    void findByIdTest() {
        Optional<Comment> optionalComment = repositoryJpa.findById(EXISTING_COMMENT_ID);
        Comment expectedComment = em.find(Comment.class, EXISTING_COMMENT_ID);
        assertThat(optionalComment).isPresent().get().isEqualTo(expectedComment);
    }

    @DisplayName("find and return a list of book comments by bookId")
    @Test
    void findAllByBookIdTest() {
        List<Comment> comments = repositoryJpa.findAllByBookId(EXISTING_BOOK_ID);
        assertThat(comments.size()).isEqualTo(EXPECTED_BOOK_1_COMMENTS_COUNT);
    }

    @DisplayName("delete author by ID")
    @Test
    void deleteByIdTest() {
        Comment existingComment = em.find(Comment.class, EXISTING_COMMENT_ID);
        assertThat(existingComment).isNotNull();
        em.detach(existingComment);

        repositoryJpa.deleteById(EXISTING_COMMENT_ID);
        Comment deletedComment = em.find(Comment.class, EXISTING_COMMENT_ID);

        assertThat(deletedComment).isNull();
    }

}
