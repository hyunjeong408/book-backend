package com.example.bookback.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class ReviewPostRequestDto {
    private String title;
    private String content;
    private String bookTitle;
    private String bookWriter;
    private Integer tag;
    private String writer_token;

    public String getWriter_token(){
        return writer_token;
    }
    public Integer getTag_id() { return tag; }

}
