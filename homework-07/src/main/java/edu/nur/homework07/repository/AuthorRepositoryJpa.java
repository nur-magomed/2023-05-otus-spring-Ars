package edu.nur.homework07.repository;

import edu.nur.homework07.model.Author;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class AuthorRepositoryJpa implements AuthorRepository {

    @PersistenceContext
    private final EntityManager em;

    public AuthorRepositoryJpa(EntityManager em) {
        this.em = em;
    }

    @Override
    public Author save(Author author) {
        if (author.getId() <= 0) {
            em.persist(author);
            return author;
        } else {
            return em.merge(author);
        }
    }

    @Override
    public Optional<Author> findById(long id) {
        return Optional.ofNullable(em.find(Author.class, id));
    }

    @Override
    public List<Author> findAll() {
        TypedQuery<Author> query = em.createQuery("SELECT a FROM Author a", Author.class);
        return query.getResultList();
    }

    @Override
    public void deleteById(long id) {
        Optional<Author> optionalAuthor = findById(id);
        if (optionalAuthor.isPresent()) {
            Author author = optionalAuthor.get();
            em.remove(author);
        }
    }

}
