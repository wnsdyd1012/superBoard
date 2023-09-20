package com.Super.Board.controller;

import com.Super.Board.dto.CommentDTO;
import com.Super.Board.entity.Comment;
import com.Super.Board.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/comments")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @GetMapping
    public ResponseEntity<List<CommentDTO>> findAll(){
        List<Comment> comments = commentService.findAll();
        List<CommentDTO> commentDTOs = comments.stream()
                .map(commentService::entityToDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(commentDTOs);
    }

    @PostMapping
    public ResponseEntity<CommentDTO> addComment(@RequestBody CommentDTO commentDTO){
        Comment addedComment = commentService.addComment(commentDTO);
        CommentDTO newComment = commentService.entityToDTO(addedComment);
        return ResponseEntity.status(HttpStatus.CREATED).body(newComment);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CommentDTO> updateComment(@PathVariable Long id, @RequestBody CommentDTO commentDTO){
        Comment changedComment = commentService.putComment(id, commentDTO);
        CommentDTO changedCommentDTO = commentService.entityToDTO(changedComment);
        return ResponseEntity.ok(changedCommentDTO);
    }
}
