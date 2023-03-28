package com.example.bookback.controller;

import com.example.bookback.dto.MemberRequestDto;
import com.example.bookback.dto.MemberResponseDto;
import com.example.bookback.dto.TokenDto;
import com.example.bookback.dto.TokenRequestDto;
import com.example.bookback.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/join")
    public ResponseEntity<MemberResponseDto> Join(@RequestBody MemberRequestDto requestDto) {
        return ResponseEntity.ok(authService.join(requestDto));
    }

    @PostMapping("/login")
    public ResponseEntity<TokenDto> Login(@RequestBody MemberRequestDto requestDto) {
        return ResponseEntity.ok(authService.login(requestDto));
    }

    @ResponseBody
    @PostMapping("/myInfo")
    public ResponseEntity<MemberResponseDto> getUserInfo(@RequestBody TokenRequestDto token) {
        return ResponseEntity.ok(authService.getInfo(token));
    }
}
