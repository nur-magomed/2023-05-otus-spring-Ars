package edu.nur.homework06.service;

import edu.nur.homework06.exception.CommentInputException;
import edu.nur.homework06.model.Book;
import edu.nur.homework06.model.Comment;
import edu.nur.homework06.repository.BookRepository;
import edu.nur.homework06.repository.CommentRepository;
import edu.nur.homework06.service.validator.CommentInputValidator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;

    private final BookRepository bookRepository;

    public CommentServiceImpl(CommentRepository commentRepository, BookRepository bookRepository) {
        this.commentRepository = commentRepository;
        this.bookRepository = bookRepository;
    }

    @Transactional
    @Override
    public Comment save(String message, long bookId) {
        Optional<Book> book = bookRepository.findById(bookId);
        CommentInputValidator.validateSaveInput(message, book);
        Comment comment = new Comment(0, book.get(), message);
        return commentRepository.save(comment);
    }

    @Transactional
    @Override
    public Comment update(long id, String title) {
        Comment comment = commentRepository.findById(id)
                .orElseThrow(() -> new CommentInputException("Comment not found with id: " + id));
        comment.setMessage(title);
        return commentRepository.save(comment);
    }

    @Transactional(readOnly = true)
    @Override
    public Comment getById(long id) {
        return commentRepository.findById(id)
                .orElseThrow(() -> new CommentInputException("Comment not found with id: " + id));
    }

    @Transactional(readOnly = true)
    @Override
    public List<Comment> findAllByBookId(long bookId) {
        return commentRepository.findAllByBookId(bookId);
    }

    @Transactional
    @Override
    public void deleteById(long id) {
        commentRepository.deleteById(id);
    }

}
