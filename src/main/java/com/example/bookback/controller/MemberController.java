package com.example.bookback.controller;

import com.example.bookback.dto.JoinMemberInfoDto;
import com.example.bookback.dto.MemberRequestDto;
import com.example.bookback.dto.MemberResponseDto;
import com.example.bookback.service.MemberService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
@RestController
@RequestMapping("/member")
public class MemberController {

    private MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

//    @GetMapping("/api/demo-web")
//    public List<String> Hello(){
//        return Arrays.asList("리액트 스프링!!! ", "연결 성공");
//    }

//    @PostMapping("/join")
//    public void Join(@RequestBody JoinMemberInfoDto joinUserdata){
//        memberService.joinUser(joinUserdata);
//    }
//
//    @PostMapping("/login")
//    public MemberResponseDto Login(@RequestBody MemberRequestDto userInfo){
//        MemberResponseDto responseRes = memberService.findUserByIdPw(userInfo);
//        return responseRes;
//    }

//    @GetMapping("/me")
//    public ResponseEntity<MemberResponseDto> getMyMemberInfo() {
//        MemberResponseDto myInfoBySecurity = memberService.getMyInfoBySecurity();
//        System.out.println("?"+myInfoBySecurity.getUser_name());
//        return ResponseEntity.ok((myInfoBySecurity));
//        // return ResponseEntity.ok(memberService.getMyInfoBySecurity());
//    }

//    @PostMapping("/nickname")
//    public ResponseEntity<MemberResponseDto> setMemberNickname(@RequestBody MemberRequestDto request) {
//        return ResponseEntity.ok(memberService.changeMemberNickname(request.getEmail(), request.getNickname()));
//    }
//
//    @PostMapping("/password")
//    public ResponseEntity<MemberResponseDto> setMemberPassword(@RequestBody ChangePasswordRequestDto request) {
//        return ResponseEntity.ok(memberService.changeMemberPassword(request.getExPassword(), request.getNewPassword()));
//    }
}
