package com.example.bookback.controller;

import com.example.bookback.dto.ReviewPostRequestDto;
import com.example.bookback.dto.ReviewResponseDto;
import com.example.bookback.dto.TokenRequestDto;
import com.example.bookback.entity.Review;
import com.example.bookback.entity.Sentence;
import com.example.bookback.service.AuthService;
import com.example.bookback.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/review")
@RequiredArgsConstructor
public class ReviewController {
    private final ReviewService reviewService;
    private final AuthService authService;

    @PostMapping("/post")
    public ResponseEntity<ReviewResponseDto> PostReview(@RequestBody ReviewPostRequestDto postRequestDto){
        TokenRequestDto tokenRequestDto = new TokenRequestDto(postRequestDto.getWriter_token());
        Integer user_sn = authService.getInfo(tokenRequestDto).getUser_sn();
        return ResponseEntity.ok(reviewService.post(postRequestDto, user_sn, postRequestDto.getTag_id()));
    }

    @GetMapping("/")
    public List<Review> getAllReviewList(){
        return reviewService.getAllReviews();
    }
}
