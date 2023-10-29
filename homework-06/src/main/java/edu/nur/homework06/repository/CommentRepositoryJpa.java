package edu.nur.homework06.repository;


import edu.nur.homework06.model.Comment;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class CommentRepositoryJpa implements CommentRepository {

    @PersistenceContext
    private final EntityManager em;

    public CommentRepositoryJpa(EntityManager em) {
        this.em = em;
    }

    @Override
    public Comment save(Comment comment) {
        if (comment.getId() <= 0) {
            em.persist(comment);
            return comment;
        } else {
            return em.merge(comment);
        }
    }

    @Override
    public Optional<Comment> findById(long id) {
        return Optional.ofNullable(em.find(Comment.class, id));
    }

    @Override
    public List<Comment> findAllByBookId(long bookId) {
        TypedQuery<Comment> query = em.createQuery("SELECT c FROM Comment c where c.bookId=:bookId", Comment.class);
        query.setParameter("bookId", bookId);
        return query.getResultList();
    }

    @Override
    public void deleteById(long id) {
        Optional<Comment> optionalComment = findById(id);
        if (optionalComment.isPresent()) {
            Comment comment = optionalComment.get();
            em.remove(comment);
        }
    }

}
