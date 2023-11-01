package ru.otus.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.dao.BookDao;
import ru.otus.dao.CommentDao;
import ru.otus.exception.NotFoundException;
import ru.otus.model.Book;
import ru.otus.model.Comment;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentDao commentDao;

    private final BookDao bookDao;

    @Override
    @Transactional(readOnly = true)
    public Comment getCommentById(Long id) {
        return commentDao.getCommentById(id).orElseThrow(() -> new NotFoundException("comment not found"));
    }

    @Override
    @Transactional
    public void addComment(Comment comment) {
        Book book = bookDao.getBookById(comment.getBook().getId()).orElseThrow(()
                -> new NotFoundException("book not found"));
        comment.setBook(book);
        commentDao.save(comment);
    }

    @Override
    @Transactional
    public List<Comment> getCommentsByBookId(Long id) {
        bookDao.getBookById(id).orElseThrow(()
                -> new NotFoundException("book not found"));
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
        getCommentById(comment.getId());
        commentDao.deleteComment(comment);
    }

    @Override
    @Transactional
    public void updateCommentById(Comment comment) {
        getCommentById(comment.getId());
        commentDao.save(comment);
    }
}
