package ru.otus.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.dao.BookRepository;
import ru.otus.dao.CommentRepository;
import ru.otus.exception.NotFoundException;
import ru.otus.model.Book;
import ru.otus.model.Comment;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;

    private final BookRepository bookRepository;

    @Override
    @Transactional(readOnly = true)
    public Comment getCommentById(Long id) {
        Comment comment = commentRepository.getCommentById(id);
        if (comment == null) {
            throw new NotFoundException("comment not found");
        }
        return comment;
    }

    @Override
    @Transactional
    public void addComment(Comment comment) {
        Book book = bookRepository.getBookById(comment.getBook().getId());
        if (book == null) {
            throw new NotFoundException("book not found");
        }
        comment.setBook(book);
        commentRepository.save(comment);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Comment> getCommentsByBookId(Long id) {
        if (bookRepository.getBookById(id) == null) {
                throw  new NotFoundException("book not found");
        }
        return commentRepository.getCommentsByBookId(id);
    }

    @Override
    @Transactional
    public void deleteCommentById(Comment comment) {
        getCommentById(comment.getId());
        commentRepository.delete(comment);
    }

    @Override
    @Transactional
    public void updateCommentById(Comment comment) {
        getCommentById(comment.getId());
        commentRepository.save(comment);
    }
}
