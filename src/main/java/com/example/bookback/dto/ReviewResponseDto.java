package com.example.bookback.dto;

import com.example.bookback.entity.Hashtag;
import com.example.bookback.entity.Member;
import com.example.bookback.entity.Review;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class ReviewResponseDto {
    private Integer reviewId;
    private String title;
    private String content;
    private Member writer;
    private String bookTitle;
    private String bookWriter;
    private Timestamp updateDate;
    private Hashtag tag;
    private Integer likeNum;
    private Integer hits;

    public ReviewResponseDto(Review review){
        this.reviewId = review.getReviewId();
        this.title = review.getTitle();
        this.content = review.getContent();
        this.writer = review.getWriter();
        this.bookTitle = review.getBookTitle();
        this.bookWriter = review.getBookWriter();
        this.updateDate = review.getUpdateDate();
        this.tag = review.getHashtag();
        this.likeNum = review.getLikeNum();
        this.hits = review.getHits();
    }
}
