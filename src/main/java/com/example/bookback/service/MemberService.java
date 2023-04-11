package com.example.bookback.service;

import com.example.bookback.dto.MemberResponseDto;
import com.example.bookback.jwt.SecurityUtil;
import com.example.bookback.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {
    @Autowired
    private MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    public MemberResponseDto getMyInfoBySecurity() {
        return memberRepository.findByUserSn(Math.toIntExact(SecurityUtil.getCurrentMemberId()))
                .map(MemberResponseDto::of)
                .orElseThrow(() -> new RuntimeException("로그인 유저 정보가 없습니다"));
    }

//    public MemberResponseDto findMemberByEmailAndPw(String email, String pw) {
//        return memberRepository.findByUserEmailAndUserPw(email, pw)
//                .map(MemberResponseDto::of)
//                .orElseThrow(() -> new RuntimeException("유저 정보가 없습니다."));
//    }
//
//    public void joinUser(JoinMemberInfoDto joinUserInfo){
//        Member member = joinUserInfo.toEntity();
//        memberRepository.save(member);
//    }

//    public MemberResponseDto findUserByIdPw(MemberRequestDto userInfo){
//        Member member = userInfo.toEntity();
//        MemberResponseDto response = memberRepository.findByUserEmailAndUserPw(member.getUserEmail(), member.getUserPw());
//        return response;
//    }
}
