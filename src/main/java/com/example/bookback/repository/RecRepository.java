package com.example.bookback.repository;

import com.example.bookback.entity.Recommend;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecRepository extends JpaRepository<Recommend, Integer> {
    Recommend findByBoard_IdAndUser_UserSn(Integer id_board, Integer user_sn);
}
