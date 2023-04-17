package com.example.bookback.controller;

import com.example.bookback.dto.*;
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

    @PutMapping("/detail/hit")
    public void Hit(@RequestBody HitUpdateRequestDto board_update){
        reviewService.updateHits(board_update.getId());
    }
    @PutMapping("/detail/recommend")
    public Boolean UpdateLikes(@RequestBody RecOrLikeRequestDto like_update){
        TokenRequestDto tokenRequestDto = new TokenRequestDto(like_update.getWriter_token());
        Integer user_sn = authService.getInfo(tokenRequestDto).getUser_sn();
        return reviewService.updateLikes(like_update.getPost_id(), user_sn);
    }
    @GetMapping("/detail/")
    public ResponseEntity<ReviewResponseDto> getReviewDetail(@PathVariable("id") Integer review_id){
        return ResponseEntity.ok(reviewService.getReview(review_id));
    }

    @GetMapping("/hot")
    public List<Review> getHotReviewList() { return reviewService.getHotReview(); }

    @GetMapping("/")
    public List<Review> getAllReviewList(){
        return reviewService.getAllReviews();
    }
}
