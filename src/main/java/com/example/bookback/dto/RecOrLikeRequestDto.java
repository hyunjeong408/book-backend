package com.example.bookback.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class RecOrLikeRequestDto {
    private Integer post_id;
    private String writer_token;
}
