package com.example.bookback.dto;

import com.example.bookback.entity.Hashtag;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class HashtagResponseDto {
    private Integer tag_id;
    private String tag_name;

    public HashtagResponseDto(Hashtag hashtag){
        this.tag_id = hashtag.getTagId();
        this.tag_name = hashtag.getTagName();
    }
}
