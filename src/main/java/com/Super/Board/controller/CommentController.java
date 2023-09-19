package com.Super.Board.controller;

import com.Super.Board.dto.CommentDTO;
import com.Super.Board.entity.Comment;
import com.Super.Board.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/comments")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @GetMapping("/api/comments")
    public List<CommentDTO> findAll(){
        List<Comment> comments = commentService.findAll();
        return comments.stream()
                .map(commentService::entityToDTO)
                .collect(Collectors.toList());
    }
}
