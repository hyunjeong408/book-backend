package com.example.bookback.repository;

import com.example.bookback.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BoardRepository extends JpaRepository<Board, Integer> {
    List<Board> findAllByOrderByUpdateDateDesc();
    List<Board> findTop10ByOrderByRecNumDesc();
}
