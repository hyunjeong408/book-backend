package com.example.bookback.controller;

import com.example.bookback.dto.*;
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

    @PutMapping("/detail/like")
    public Boolean UpdateLikes(@RequestBody LikeSentenceRequestDto like_update){
        TokenRequestDto tokenRequestDto = new TokenRequestDto(like_update.getWriter_token());
        Integer user_sn = authService.getInfo(tokenRequestDto).getUser_sn();
        return sentenceService.updateLikes(like_update.getPost_id(), user_sn, like_update.getUpdate_n());
    }
    @GetMapping("/detail/")
    public Boolean chkSentenceLiked(@RequestParam Integer id, @RequestParam String token){
        TokenRequestDto tokenRequestDto = new TokenRequestDto(token);
        Integer user_sn = authService.getInfo(tokenRequestDto).getUser_sn();
        return sentenceService.chkSentenceLiked(id,user_sn);
    }

    @GetMapping("/recommend")
    public List<Sentence> getRecSentenceList(@RequestParam String token){
        if(token.length()>0){
            TokenRequestDto tokenRequestDto = new TokenRequestDto(token);
            Integer user_sn = authService.getInfo(tokenRequestDto).getUser_sn();
            return sentenceService.getSentencesToRecommend(user_sn);
        }
        else{//토큰 없을 때(로그인 x)
            return sentenceService.getTop10Sentences();
        }
        //return sentenceService.getAllSentences();
    }
    @GetMapping("/")
    public List<Sentence> getAllSentenceList(){
        return sentenceService.getAllSentences();
    }
}
