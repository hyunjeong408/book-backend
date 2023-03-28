package com.example.bookback.dto;

import com.example.bookback.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Optional;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class MemberResponseDto {
    private Integer user_sn;
    private String user_email;
//    private String user_pw;
    private String user_name;

    public MemberResponseDto(Member member){
        this.user_sn = member.getUserSn();
        this.user_email = member.getUserEmail();
//        this.user_pw = member.getUserPw();
        this.user_name = member.getUserName();
    }

    public static MemberResponseDto of(Member member){
        return MemberResponseDto.builder()
                .user_sn(member.getUserSn())
                .user_email(member.getUserEmail())
                .user_name(member.getUserName())
                .build();
    }

}
