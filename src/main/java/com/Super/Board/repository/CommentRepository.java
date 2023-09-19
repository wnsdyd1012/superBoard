package com.Super.Board.repository;

import com.Super.Board.dto.CommentDTO;
import com.Super.Board.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long > {

    List<Comment> findByAuthor(String author);
}
