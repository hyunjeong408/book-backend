package com.example.bookback.dto;

import com.example.bookback.entity.Board;
import com.example.bookback.entity.Member;
import lombok.Data;

import java.sql.Timestamp;
@Data
public class BoardDto {
    private Integer id;
    private String title;
    private String content;
    private Member writer;
    private Timestamp updateDate;
    private Integer viewCnt;
    private Integer likeCnt;

    public BoardDto(Board board){
        this.id = board.getId();
        this.title = board.getTitle();
        this.content = board.getContent();
//        this.writer = board.getWriter();
        this.updateDate = board.getUpdateDate();
        this.viewCnt = board.getViewCnt();
        this.likeCnt = board.getLikeCnt();
    }

}
