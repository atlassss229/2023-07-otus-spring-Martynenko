package ru.otus.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.dao.CommentDao;
import ru.otus.model.Comment;

import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentDao commentDao;

    @Override
    @Transactional(readOnly = true)
    public Comment getCommentById(Long id) {
        Optional<Comment> comment = commentDao.getCommentById(id);
        if (comment.isPresent()) {
            return comment.get();
        } else {
            return null;
        }

    }

    @Override
    @Transactional
    public void saveComment(Comment comment) {
        commentDao.save(comment);
    }

    @Override
    @Transactional
    public List<Comment> getCommentsByBookId(Long id) {
        List<Comment> commentList = commentDao.getCommentsByBookId(id);
        if (commentList.isEmpty()) {
            return null;
        } else {
            return commentList;
        }
    }

    @Override
    @Transactional
    public void deleteCommentById(Comment comment) {
        commentDao.deleteComment(comment);
    }

    @Override
    @Transactional
    public void updateCommentById(Comment comment) {
        commentDao.save(comment);
    }
}
