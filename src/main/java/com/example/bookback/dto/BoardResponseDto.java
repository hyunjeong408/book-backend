package com.example.bookback.dto;

import com.example.bookback.entity.Board;
import com.example.bookback.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class BoardResponseDto {
    private Integer id;
    private String title;
    private String content;
    private Member writer;
    private Timestamp updateDate;
    private Integer recNum;
    private Integer hits;

    public BoardResponseDto(Board board){
        this.id = board.getId();
        this.title = board.getTitle();
        this.content = board.getContent();
        this.writer = board.getWriter();
        this.updateDate = board.getUpdateDate();
        this.recNum = board.getRecNum();
        this.hits = board.getHits();
    }

}
