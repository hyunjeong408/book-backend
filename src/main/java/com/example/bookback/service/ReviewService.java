package com.example.bookback.service;

import com.example.bookback.dto.ReviewPostRequestDto;
import com.example.bookback.dto.ReviewResponseDto;
import com.example.bookback.entity.Hashtag;
import com.example.bookback.entity.Member;
import com.example.bookback.entity.Review;
import com.example.bookback.repository.HashtagRepository;
import com.example.bookback.repository.MemberRepository;
import com.example.bookback.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ReviewService {
    private final MemberRepository memberRepository;
    private final HashtagRepository tagRepository;
    private final ReviewRepository reviewRepository;

    public ReviewResponseDto post(ReviewPostRequestDto requestDto, Integer user_sn, Integer tag_id){
        Member writer = memberRepository.findByUserSn(user_sn).orElseThrow(()->new UsernameNotFoundException("이용자를 DB에서 찾을 수 없습니다"));
        Hashtag tag = tagRepository.findByTagId(tag_id).orElseThrow();
        Review review = Review.builder()
                .title(requestDto.getTitle())
                .content(requestDto.getContent())
                .writer(writer)
                .b_title(requestDto.getBookTitle())
                .b_writer(requestDto.getBookWriter())
                .tag(tag)
                .updateDate(Timestamp.valueOf(LocalDateTime.now()))
                .build();
        return new ReviewResponseDto(reviewRepository.save(review));
    }

    public List<Review> getAllReviews(){
        return reviewRepository.findAllByOrderByUpdateDateDesc();
    }
}
