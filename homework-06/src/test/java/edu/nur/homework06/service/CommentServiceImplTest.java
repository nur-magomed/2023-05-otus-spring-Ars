package edu.nur.homework06.service;

import edu.nur.homework06.model.Author;
import edu.nur.homework06.model.Book;
import edu.nur.homework06.model.Comment;
import edu.nur.homework06.model.Genre;
import edu.nur.homework06.repository.CommentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@DisplayName("Comment service should ")
@SpringBootTest(properties = "spring.shell.interactive.enabled=false")
class CommentServiceImplTest {

    @MockBean
    private CommentRepository commentRepository;

    @Autowired
    private CommentServiceImpl commentService;

    private static final Set<Author> EXPECTED_AUTHOR_SET = new HashSet<>();

    private static final List<Comment> EXPECTED_COMMENT_LIST = new ArrayList<>();

    private static final Genre GENRE = new Genre(1L, "Test genre", new Date(), new Date());

    private static final Book EXPECTED_BOOK = new Book(1L, "Test book", GENRE, EXPECTED_AUTHOR_SET,
            EXPECTED_COMMENT_LIST, new Date(), new Date());


    private static final String COMMENT_TITLE = "Test comment";

    @BeforeEach
    void setUp() {
        EXPECTED_COMMENT_LIST.add(new Comment(5, EXPECTED_BOOK, "Test comment5"));
        EXPECTED_COMMENT_LIST.add(new Comment(6, EXPECTED_BOOK, "Test comment6"));
    }

    @DisplayName("save new comment")
    @Test
    void save() {
        Comment expectedComment = new Comment(1, EXPECTED_BOOK, COMMENT_TITLE);
        when(commentRepository.save(any(Comment.class))).thenReturn(expectedComment);
        Comment saved = commentService.save(COMMENT_TITLE, EXPECTED_BOOK.getId());
        assertThat(expectedComment).usingRecursiveComparison().isEqualTo(saved);
    }

    @DisplayName("update a comment")
    @Test
    void update() {

        Comment expectedComment = new Comment(1, EXPECTED_BOOK, COMMENT_TITLE);
        when(commentRepository.save(any(Comment.class))).thenReturn(expectedComment);
        when(commentRepository.findById(expectedComment.getId())).thenReturn(Optional.of(expectedComment));
        Comment updated = commentService.update(expectedComment.getId(), expectedComment.getMessage());
        assertThat(expectedComment).usingRecursiveComparison().isEqualTo(updated);
    }

    @DisplayName("get a comment by ID")
    @Test
    void getById() {
        Comment expectedComment = new Comment(1, EXPECTED_BOOK, COMMENT_TITLE);
        when(commentRepository.findById(expectedComment.getId())).thenReturn(Optional.of(expectedComment));
        assertEquals(expectedComment, commentService.getById(expectedComment.getId()));
    }

    @DisplayName("get list of all comments")
    @Test
    void getAll() {
        when(commentRepository.findAllByBookId(EXPECTED_BOOK.getId())).thenReturn(EXPECTED_COMMENT_LIST);
        assertThat(EXPECTED_COMMENT_LIST).containsExactlyElementsOf(commentService.findAllByBookId(EXPECTED_BOOK.getId()));
    }

    @DisplayName("delete a comment by ID")
    @Test
    void deleteById() {
        commentService.deleteById(1L);
        verify(commentRepository, times(1)).deleteById(1L);
    }

}