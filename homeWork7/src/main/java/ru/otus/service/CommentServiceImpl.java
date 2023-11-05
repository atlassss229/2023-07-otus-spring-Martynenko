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
        return commentRepository.findById(id).orElseThrow(() -> new NotFoundException("comment not found"));
    }

    @Override
    @Transactional
    public void addComment(Comment comment) {
        Book book = bookRepository.findById(comment.getBook().getId()).orElseThrow(()
                -> new NotFoundException("book not found"));
        comment.setBook(book);
        commentRepository.save(comment);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Comment> getCommentsByBookId(Long id) {
        bookRepository.findById(id).orElseThrow(()
                -> new NotFoundException("book not found"));
        return commentRepository.findByBookId(id);
    }

    @Override
    @Transactional
    public void deleteCommentById(Comment comment) {
        commentRepository.delete(comment);
    }

    @Override
    @Transactional
    public void updateCommentById(Comment comment) {
        Comment commentFromDb = commentRepository.findById(comment.getId()).orElseThrow(()
                -> new NotFoundException("comment not found"));
        commentFromDb.setText(comment.getText());
        commentRepository.save(commentFromDb);
    }
}
