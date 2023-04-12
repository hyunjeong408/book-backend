package com.example.bookback.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Getter
@NoArgsConstructor
@DynamicInsert
@Table(name="review")
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="idreview", nullable = false)
    private Integer reviewId;

    @Column(name="title", nullable = false)
    private String title;

    @Column(name="content", nullable = false)
    private String content;

    @ManyToOne
    @JoinColumn(name="writer", nullable = false)
    private Member writer;

    @Column(name="book_title", nullable = false)
    private String bookTitle;

    @Column(name="book_writer", nullable = false)
    private String bookWriter;

    @Column(name="update_date", nullable = false)
    private Timestamp updateDate;

    @Column(name="like_num")
    private Integer likeNum = 0;

    @ManyToOne // Many = Board, User = One 한명의 유저는 여러개의 게시글을 쓸 수 있다.
    @JoinColumn(name="reviewtag", nullable = false)
    private Hashtag hashtag;

    @Column(name="hits")
    private Integer hits=0;

    @Builder
    public Review(String title, String content, String b_title, String b_writer, Member writer, Timestamp updateDate, Hashtag tag){
        this.title = title;
        this.content = content;
        this.bookTitle = b_title;
        this.bookWriter = b_writer;
        this.writer = writer;
        this.updateDate = updateDate;
        this.likeNum = 0;
        this.hashtag = tag;
        this.hits = 0;
    }

    public void updateHits(){
        this.hits = this.hits+1;
    }

    public void updatelikeNum(){
        this.likeNum += this.likeNum+1;
    }
}
