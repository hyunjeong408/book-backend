package com.example.bookback.repository;

import com.example.bookback.entity.LikeReview;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikeReviewRepository extends JpaRepository<LikeReview, Integer> {
    LikeReview findByReview_ReviewIdAndUser_UserSn(Integer id_review, Integer user_sn);
}
