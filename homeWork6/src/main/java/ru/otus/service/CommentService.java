package ru.otus.service;

import ru.otus.model.Comment;

import java.util.List;

public interface CommentService {
    Comment getCommentById(Long id);

    void saveComment(Comment comment);

    List<Comment> getCommentsByBookId(Long id);

    void deleteCommentById(Comment comment);

    void updateCommentById(Comment comment);
}
