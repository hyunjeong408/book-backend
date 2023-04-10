package com.example.bookback.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Getter
@NoArgsConstructor
@DynamicInsert
@Table(name="hashtag")
public class Hashtag {
    @Id
    @Column(name="tag_id", nullable = false)
    private Integer tagId;

    @Column(name="tag_name", nullable = false)
    private String tagName;

    public Hashtag(Integer id, String name){
        this.tagId = id;
        this.tagName = name;
    }
}
