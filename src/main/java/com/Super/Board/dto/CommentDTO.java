package com.Super.Board.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CommentDTO {

    private Long id;
    private String author;
    private String content;
}
