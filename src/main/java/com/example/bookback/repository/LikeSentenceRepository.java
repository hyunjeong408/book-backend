package com.example.bookback.repository;

import com.example.bookback.entity.LikeSentence;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikeSentenceRepository extends JpaRepository<LikeSentence, Integer> {
    LikeSentence findBySentence_IdAndUser_UserSn(Integer id_sentence, Integer user_sn);
}
