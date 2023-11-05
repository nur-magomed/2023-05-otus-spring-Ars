package edu.nur.homework06.util;

import edu.nur.homework06.model.Comment;
import edu.nur.homework06.service.CommentService;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

import java.util.List;

@ShellComponent
public class CommentShell {

    private final CommentService commentService;

    public CommentShell(CommentService commentService) {
        this.commentService = commentService;
    }

    @ShellMethod(value = "List all comments by bookId: comments id", key = "comments")
    public String commentsByBookId(@ShellOption(value = "bookId", defaultValue = "-1") long bookId) {
        List<Comment> comments = commentService.findAllByBookId(bookId);
        StringBuilder sb = new StringBuilder();
        comments.forEach(g -> sb.append(prepareView(g)).append("\n"));
        return sb.toString();
    }

    @ShellMethod(value = "Add comment to book: comment new \"bookID\" \"message\" ", key = "comment new")
    public String save(@ShellOption(value = "bookId", defaultValue = "-1") long bookId,
                       @ShellOption(value = "message", defaultValue = "") String message) {
        Comment saved = commentService.save(message, bookId);
        return "Comment saved successfully." + prepareView(saved);
    }

    @ShellMethod(value = "Update comment: comment update id \"Edited message\"", key = "comment update")
    public String update(@ShellOption(value = "id", defaultValue = "-1") long id,
                       @ShellOption(value = "message", defaultValue = "") String message) {
        Comment updated = commentService.update(id, message);
        return "Comment updated successfully. " + prepareView(updated);
    }

    @ShellMethod(value = "Get comment by Id: comment get id", key = "comment get")
    public String getById(@ShellOption(value = "id", defaultValue = "-1") long id) {
        Comment comment = commentService.getById(id);
        return prepareView(comment);
    }

    @ShellMethod(value = "Delete comment: comment delete id", key = "comment delete")
    public String delete(@ShellOption(value = "id", defaultValue = "-1") long id) {
        commentService.deleteById(id);
        return String.format("comment deleted successfully id:%d", id);
    }

    private String prepareView(Comment comment) {
        return String.format("Comment id: %d message: %s", comment.getId(), comment.getMessage());
    }
}
