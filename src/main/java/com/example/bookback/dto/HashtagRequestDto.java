package com.example.bookback.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class HashtagRequestDto {
    private Integer tag_id;
    public HashtagRequestDto(Integer tag_id){
        this.tag_id = tag_id;
    }
}
