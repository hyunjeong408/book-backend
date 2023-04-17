package com.example.bookback.repository;

import com.example.bookback.entity.Board;
import com.example.bookback.entity.Sentence;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SentenceRepository extends JpaRepository<Sentence, Integer> {
    List<Sentence> findAllByOrderByUpdateDateDesc();

    List<Sentence> findTop10ByOrderByUpdateDateDesc();
}
