package com.example.bookback.entity;

import io.jsonwebtoken.lang.Assert;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Getter
@NoArgsConstructor
@DynamicInsert
@Table(name="board")
public class Board {
    @PrePersist
    public void prePersist(){
        this.recNum = 0;
        this.hits = 0;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id", nullable = false)
    private Integer id;
    @Column(name="title", nullable = false)
    private String title;
    @Column(name="content", nullable = false)
    private String content;
    @ManyToOne // Many = Board, User = One 한명의 유저는 여러개의 게시글을 쓸 수 있다.
    @JoinColumn(name="writer", nullable = false)
    private Member writer;
    @Column(name="update_date", nullable = false)
    private Timestamp updateDate;

    @Column(name="rec_num")
    private Integer recNum = 0;

    @Column(name="hits")
    private Integer hits=0;


    @Builder
    public Board(String title, String content, Member writer, Timestamp updateDate){
        this.title = title;
        this.content = content;
        this.writer = writer;
        this.updateDate = updateDate;
        this.recNum = 0;
        this.hits = 0;
    }
}
