package com.example.bookback.service;

import com.example.bookback.dto.ReviewPostRequestDto;
import com.example.bookback.dto.ReviewResponseDto;
import com.example.bookback.entity.*;
import com.example.bookback.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class ReviewService {
    private final MemberRepository memberRepository;
    private final HashtagRepository tagRepository;
    private final ReviewRepository reviewRepository;
    private final LikeReviewRepository likeReviewRepository;
    private final UserLikesRepository userLikesRepository;

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

    @Transactional
    public void updateHits(Integer review_id){
        Review review = reviewRepository.findById(review_id).orElseThrow();
        review.updateHits();
    }

    @Transactional
    public Boolean updateLikes(Integer review_id, Integer user_sn){
        Review review = reviewRepository.findById(review_id).orElseThrow();
        Member user = memberRepository.findByUserSn(user_sn).orElseThrow(()->new UsernameNotFoundException("이용자를 DB에서 찾을 수 없습니다"));
        if (likeReviewRepository.findByReview_ReviewIdAndUser_UserSn(review_id, user_sn) == null) { //추천하지 않은 경우
            LikeReview likeReview = LikeReview.builder()
                    .review(review)
                    .user(user)
                    .build();
            likeReviewRepository.save(likeReview);
            review.updatelikeNum();
            Optional<UserLikes> userLikes = userLikesRepository.findByUser_UserSn(user_sn);
            if(userLikes.isEmpty()){
                UserLikes newUserLikes = UserLikes.builder().user(user).build();
                newUserLikes.updateLikes(review.getHashtag().getTagId());
                userLikesRepository.save(newUserLikes);
            }
            else{
                userLikes.get().updateLikes(review.getHashtag().getTagId());
            }
            return true;
        }
        else {
            return false;
        }
    }
    public List<Review> getHotReview(){ return reviewRepository.findTop10ByOrderByLikeNumDesc(); }
    public ReviewResponseDto getReview(Integer reviewId){
        Optional<Review> reviewOptional = reviewRepository.findById(reviewId);
        Review review = reviewOptional.get();
        return new ReviewResponseDto(review);

    }

    public List<Review> getAllReviews(){
        return reviewRepository.findAllByOrderByUpdateDateDesc();
    }
}
