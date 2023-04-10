package com.example.bookback.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class SentencePostRequestDto {
    private String bookTitle;
    private String bookWriter;
    private String content;
    private Integer tag;
    private String writer_token;

    public String getWriter_token(){
        return writer_token;
    }
    public Integer getTag_id() { return tag; }
}
