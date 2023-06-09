package com.example.bookback.repository;

import com.example.bookback.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Integer> {
    List<Review> findAllByOrderByUpdateDateDesc();

    List<Review> findTop10ByOrderByLikeNumDesc();
}
