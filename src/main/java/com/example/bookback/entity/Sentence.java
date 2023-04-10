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
@Table(name="sentence")
public class Sentence {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="idsentence", nullable = false)
    private Integer id;

    @Column(name="content", nullable = false)
    private String content;

    @Column(name="book_title", nullable = false)
    private String title;

    @Column(name="book_writer", nullable = false)
    private String bookWriter;

    @ManyToOne // Many = Board, User = One 한명의 유저는 여러개의 게시글을 쓸 수 있다.
    @JoinColumn(name="writer", nullable = false)
    private Member writer;

    @Column(name="update_date", nullable = false)
    private Timestamp updateDate;

    @Column(name="like_num")
    private Integer likeNum = 0;

    @ManyToOne // Many = Board, User = One 한명의 유저는 여러개의 게시글을 쓸 수 있다.
    @JoinColumn(name="sentence_tag", nullable = false)
    private Hashtag hashtag;

    @Builder
    public Sentence(String content, String title, String b_writer, Member writer, Timestamp updateDate, Hashtag tag){
        this.content = content;
        this.title = title;
        this.bookWriter = b_writer;
        this.writer = writer;
        this.updateDate = updateDate;
        this.likeNum = 0;
        this.hashtag = tag;
    }
}
