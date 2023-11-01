package ru.otus.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.otus.model.Comment;

import java.util.List;
import java.util.Optional;


@Repository
@RequiredArgsConstructor
public class CommentDaoImpl implements CommentDao {

    @PersistenceContext
    private final EntityManager entityManager;

    @Override
    public Comment save(Comment comment) {
        if (comment.getId() == null) {
            entityManager.persist(comment);
            return comment;
        } else {
            return entityManager.merge(comment);
        }
    }

    @Override
    public Optional<Comment> getCommentById(Long id) {
        TypedQuery<Comment> query = entityManager.createQuery(
                "SELECT c FROM Comment c WHERE c.id = :id", Comment.class);
        query.setParameter("id", id);
        return Optional.ofNullable(query.getResultList().isEmpty() ? null : query.getResultList().get(0));
    }

    @Override
    public List<Comment> getCommentsByBookId(Long id) {
        TypedQuery<Comment> query = entityManager.createQuery("" +
                "select c from Comment c WHERE c.book.id = :id", Comment.class);
        query.setParameter("id", id);
        return query.getResultList();
    }

    @Override
    public void deleteComment(Comment comment) {
        entityManager.remove(comment);
    }
}

