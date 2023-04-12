package com.example.bookback.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class LikeSentenceRequestDto {
    private Integer post_id;
    private String writer_token;
    private Integer update_n;
}
