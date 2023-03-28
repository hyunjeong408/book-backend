package com.example.bookback.dto;

import com.example.bookback.Authority;
import com.example.bookback.entity.Member;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;

@NoArgsConstructor
@Getter

public class MemberRequestDto {
    private String user_email;
    private String user_pw;
    private String user_name;

//    public Member toEntity(){
//        return Member.builder()
//                .user_email(user_email)
//                .user_pw(user_pw)
//                .build();
//    }

    public Member toMember(PasswordEncoder passwordEncoder) {
        return Member.builder()
                .user_email(user_email)
                .user_pw(passwordEncoder.encode(user_pw))
                .user_name(user_name)
                .authority(Authority.ROLE_USER)
                .build();
    }

    public UsernamePasswordAuthenticationToken toAuthentication() {
        return new UsernamePasswordAuthenticationToken(user_email, user_pw);
    }
}
