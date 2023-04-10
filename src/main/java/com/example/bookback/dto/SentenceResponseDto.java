package com.example.bookback.dto;

import com.example.bookback.entity.Hashtag;
import com.example.bookback.entity.Member;
import com.example.bookback.entity.Sentence;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class SentenceResponseDto {
    private Integer sentenceId;
    private Member writer;
    private String bookTitle;
    private String bookWriter;
    private String content;
    private Timestamp updateDate;
    private Hashtag tag;
    private Integer likeNum;

    public SentenceResponseDto(Sentence sentence){
        this.sentenceId = sentence.getId();
        this.writer = sentence.getWriter();
        this.content = sentence.getContent();
        this.bookTitle = sentence.getTitle();
        this.bookWriter = sentence.getBookWriter();
        this.updateDate = sentence.getUpdateDate();
        this.tag = sentence.getHashtag();
        this.likeNum = sentence.getLikeNum();
    }
}
