package com.example.bookback.repository;

import com.example.bookback.entity.Hashtag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface HashtagRepository extends JpaRepository<Hashtag, Integer> {
    Optional<Hashtag> findByTagId(Integer tagId);
}
