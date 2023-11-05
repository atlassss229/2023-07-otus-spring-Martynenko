package ru.otus.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.model.Comment;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {


    List<Comment> findByBookId(Long id);
}
