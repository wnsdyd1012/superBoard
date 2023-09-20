package com.Super.Board.controller;

import com.Super.Board.dto.CommentDTO;
import com.Super.Board.entity.Comment;
import com.Super.Board.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.query.Jpa21Utils;
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
    public ResponseEntity<List<CommentDTO>> findAll(){ // DTO? Entity?
        List<Comment> comments = commentService.findAll();
        List<CommentDTO> commentDTOs = comments.stream()
                .map(commentService::entityToDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(commentDTOs);
    }

    @PostMapping
    public ResponseEntity<Comment> addComment(@RequestBody CommentDTO commentDTO){
        Comment commentEntity = commentService.DTOToEntity(commentDTO);
        Comment newComment = commentService.addComment(commentEntity);
        return ResponseEntity.status(HttpStatus.CREATED).body(newComment);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CommentDTO> updateComment(@PathVariable Long id, @RequestBody CommentDTO commentDTO){
        Comment changedComment = commentService.putComment(id, commentDTO);
        CommentDTO changedCommentDTO = commentService.entityToDTO(changedComment);
        return ResponseEntity.ok(changedCommentDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteComment(@PathVariable Long id){
        boolean deleted = commentService.deleteComment(id);
        if(deleted) return ResponseEntity.noContent().build();
        else return ResponseEntity.notFound().build();
    }
}
