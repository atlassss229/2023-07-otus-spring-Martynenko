package ru.otus.dao;

import ru.otus.model.Comment;

import java.util.List;
import java.util.Optional;

public interface CommentDao {
    void save(Comment comment);

    Optional<Comment> getCommentById(Long id);

    List<Comment> getCommentsByBookId(Long id);

    void deleteComment(Comment comment);

    void update(Comment comment);
}
