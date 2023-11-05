package ru.otus.dao;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ContextConfiguration;
import ru.otus.model.Comment;

import java.util.List;

@DataJpaTest
public class CommentRepositoryTest {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private BookRepository bookRepository;

    @Test
    public void getAllCommentsTest() {
        List<Comment> commentList = commentRepository.findByBookId(1L);
        Assertions.assertEquals("Martins is the worst", commentList.get(0).getText());
    }

    @Test
    public void getCommentByIdTest() {
        Comment comment = commentRepository.findById(1L).get();
        Assertions.assertEquals("Martins is the worst", comment.getText());
    }

    @Test
    public void addCommentTest() {
        Comment comment = new Comment("wow!!!", bookRepository.findById(1L).get());
        commentRepository.save(comment);
        comment = commentRepository.findById(5L).get();
        Assertions.assertEquals("wow!!!", comment.getText());
    }

    @Test
    public void deleteBCommentByIdTest() {
        commentRepository.delete(commentRepository.findById(1L).get());
        Assertions.assertTrue(commentRepository.findById(1L).isEmpty());
    }
}
