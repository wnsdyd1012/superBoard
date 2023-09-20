package com.Super.Board.controller;

import com.Super.Board.dto.CommentDTO;
import com.Super.Board.entity.Comment;
import com.Super.Board.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/comments")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @GetMapping
    public List<CommentDTO> findAll(){
        List<Comment> comments = commentService.findAll();
        return comments.stream()
                .map(commentService::entityToDTO)
                .collect(Collectors.toList());
    }

    @PostMapping
    public CommentDTO addComment(@RequestBody CommentDTO commentDTO){
        Comment addComment = commentService.addComment(commentDTO);
        return commentService.entityToDTO(addComment);
    }
}
