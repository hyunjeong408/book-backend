package com.example.bookback.controller;

import com.example.bookback.dto.SentencePostRequestDto;
import com.example.bookback.dto.SentenceResponseDto;
import com.example.bookback.dto.TokenRequestDto;
import com.example.bookback.entity.Sentence;
import com.example.bookback.service.AuthService;
import com.example.bookback.service.SentenceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sentence")
@RequiredArgsConstructor
public class SentenceController {

    private final SentenceService sentenceService;
    private final AuthService authService;

    @PostMapping("/post")
    public ResponseEntity<SentenceResponseDto> PostSentence(@RequestBody SentencePostRequestDto postRequestDto){
        TokenRequestDto tokenRequestDto = new TokenRequestDto(postRequestDto.getWriter_token());
        Integer user_sn = authService.getInfo(tokenRequestDto).getUser_sn();
        return ResponseEntity.ok(sentenceService.post(postRequestDto, user_sn, postRequestDto.getTag_id()));
    }

    @GetMapping("/")
    public List<Sentence> getAllSentenceList(){
        return sentenceService.getAllSentences();
    }
}
