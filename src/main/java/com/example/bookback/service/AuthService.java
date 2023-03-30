package com.example.bookback.service;

import com.example.bookback.dto.MemberRequestDto;
import com.example.bookback.dto.MemberResponseDto;
import com.example.bookback.dto.TokenDto;
import com.example.bookback.dto.TokenRequestDto;
import com.example.bookback.entity.Member;
import com.example.bookback.jwt.SecurityUtil;
import com.example.bookback.jwt.TokenProvider;
import com.example.bookback.repository.MemberRepository;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.Principal;

@Service
@RequiredArgsConstructor
@Transactional
public class AuthService {
    private final AuthenticationManagerBuilder managerBuilder;
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenProvider tokenProvider;

    public MemberResponseDto join(MemberRequestDto requestDto) {
        if (memberRepository.existsByUserEmail(requestDto.getUser_email())) {
            throw new RuntimeException("이미 가입되어 있는 유저입니다");
        }

        Member member = requestDto.toMember(passwordEncoder);
        return new MemberResponseDto(memberRepository.save(member));
    }

    public TokenDto login(MemberRequestDto requestDto) {
        UsernamePasswordAuthenticationToken authenticationToken = requestDto.toAuthentication();

        Authentication authentication = managerBuilder.getObject().authenticate(authenticationToken);

        return tokenProvider.generateTokenDto(authentication);
    }

    public MemberResponseDto getInfo(TokenRequestDto tokenDto) {
        Integer user_sn = tokenProvider.getAllClaimsFromToken(tokenDto.getToken());
        return memberRepository.findByUserSn(user_sn)
                .map(MemberResponseDto::of)
                .orElseThrow(() -> new RuntimeException("로그인 유저 정보가 없습니다"));
    }
}
