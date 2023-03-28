package com.example.bookback.dto;

import com.example.bookback.entity.Member;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter

public class JoinMemberInfoDto {
    private String user_email;
    private String user_pw;
    private String user_name;

    public Member toEntity(){
        return Member.builder()
                .user_email(user_email)
                .user_pw(user_pw)
                .user_name(user_name)
                .build();
    }
}

