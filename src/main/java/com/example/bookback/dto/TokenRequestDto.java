package com.example.bookback.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter

public class TokenRequestDto {
    private String token;
    public TokenRequestDto(String token){
        this.token = token;
    }
}
