package com.Super.Board.service;

import com.Super.Board.dto.CommentDTO;
import com.Super.Board.entity.Comment;
import com.Super.Board.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class CommentService {

    private final CommentRepository commentRepository;

    public Comment save(Comment comment){
        return commentRepository.save(comment);
    }

    public List<Comment> findAll(){
        List<Comment> allComments = commentRepository.findAll();
        return allComments;
    }

    public Optional<Comment> findById(Long id){
        return commentRepository.findById(id);
    }

    public List<Comment> findByAuthor(String author){
        return commentRepository.findByAuthor(author);
    }

    public Comment addComment(CommentDTO commentDTO){
        Comment comment = this.DTOToEntity(commentDTO);
        return commentRepository.save(comment);
    }

    public Comment putComment(Long id, CommentDTO commentDTO) {
        Optional<Comment> optionalComment = commentRepository.findById(id);

        if(optionalComment.isPresent()){
            Comment originComment = optionalComment.get();

            originComment.setAuthor(commentDTO.getAuthor());
            originComment.setContent(commentDTO.getContent());

            return commentRepository.save(originComment);
        } else {
            throw new RuntimeException("Not Found");
        }
    }

    public CommentDTO entityToDTO(Comment comment){
        CommentDTO commentDTO = new CommentDTO();
        commentDTO.setId(comment.getId());
        commentDTO.setAuthor(comment.getAuthor());
        commentDTO.setContent(comment.getContent());
        return commentDTO;
    }

    public Comment DTOToEntity(CommentDTO commentDTO){
        Comment comment = new Comment();
        comment.setId(commentDTO.getId());
        comment.setAuthor(commentDTO.getAuthor());
        comment.setContent(commentDTO.getContent());
        return comment;
    }
}
