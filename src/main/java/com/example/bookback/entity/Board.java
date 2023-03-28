package com.example.bookback.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Getter
@NoArgsConstructor
@Table(name="board")
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id", nullable = false)
    private Integer id;
    @Column(name="title", nullable = false)
    private String title;
    @Column(name="content", nullable = false)
    private String content;
//    @Column(name="writer")
//    private Member writer;
    @Column(name="updateDate", nullable = false)
    private Timestamp updateDate;
    @Column(name="viewCnt", nullable = false)
    private Integer viewCnt;
    @Column(name="likeCnt", nullable = false)
    private Integer likeCnt;

    public Board(String title, String content, Member writer, Timestamp updateDate){
        this.title = title;
        this.content = content;
//        this.writer = writer;
        this.updateDate = updateDate;
    }
}
