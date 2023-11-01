package ru.otus.dao;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import ru.otus.model.Book;
import ru.otus.model.Comment;

import java.util.List;
import java.util.Optional;

@DataJpaTest
@Import({CommentDaoImpl.class, BookDaoImpl.class})
public class CommentDaoTest {

    @Autowired
    private CommentDao commentDao;

    @Autowired
    private BookDao bookDao;

    @Test
    public void getAllCommentsTest() {
        List<Comment> commentList = commentDao.getCommentsByBookId(1L);
        Assertions.assertEquals("Martins is the worst", commentList.get(0).getText());
    }

    @Test
    public void getCommentByIdTest() {
        Comment comment = commentDao.getCommentById(1L).get();
        Assertions.assertEquals("Martins is the worst", comment.getText());
    }

    @Test
    public void addCommentTest() {
        Comment comment = new Comment("wow!!!", bookDao.getBookById(1L).get());
        commentDao.save(comment);
        comment = commentDao.getCommentById(5L).get();
        Assertions.assertEquals("wow!!!", comment.getText());
    }

    @Test
    public void deleteBCommentByIdTest() {
        commentDao.deleteComment(commentDao.getCommentById(1L).get());
        Assertions.assertTrue(commentDao.getCommentById(1L).isEmpty());
    }
}
