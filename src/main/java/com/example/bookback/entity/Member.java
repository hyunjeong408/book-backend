package com.example.bookback.entity;

import com.example.bookback.Authority;
import io.jsonwebtoken.Claims;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "user")
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_sn", nullable = false)
    private Integer userSn;

    @Column(name = "user_email", unique = true, nullable = false)
    private String userEmail;

    @Column(name = "user_pw", nullable = false)
    private String userPw;

    @Column(name = "user_name", nullable = false)
    private String userName;

    @Enumerated(EnumType.STRING)
    private Authority authority;

    @Builder
    public Member(String user_email, String user_pw, String user_name, Authority authority){
        System.out.println(user_email+user_name+user_pw);
        this.userEmail = user_email;
        this.userPw = user_pw;
        this.userName = user_name;
        this.authority = authority;
    }

    public Member(Claims claims){
        this.userEmail = claims.get("userEmail").toString();
        this.userPw = claims.get("userPw").toString();
        this.userName = claims.get("userName").toString();
        this.authority = Authority.ROLE_USER;
    }
//    @Builder
//    public Member(String user_email, String user_pw){
//        this.userEmail = user_email;
//        this.userPw = user_pw;
//    }
}
