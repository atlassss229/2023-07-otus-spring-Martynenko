package ru.otus.service;

import org.springframework.transaction.annotation.Transactional;
import ru.otus.model.Comment;

import java.util.List;

public interface CommentService {
    @Transactional(readOnly = true)
    Comment getCommentById(Long id);

    @Transactional
    void saveComment(Comment comment);

    @Transactional
    List<Comment> getCommentsByBookId(Long id);

    @Transactional
    void deleteCommentById(Long id);

    @Transactional
    void updateCommentById(Comment comment);
}
