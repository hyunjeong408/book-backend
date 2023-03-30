package com.example.bookback.dto;

import com.example.bookback.entity.Board;
import com.example.bookback.entity.Member;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
public class BoardPostRequestDto {
    private String title;
    private String content;
    private String writer_token;

    public String getWriter_token(){
        return writer_token;
    }
    public String getContent(){
        return content;
    }

    public String getTitle(){
        return title;
    }

}
