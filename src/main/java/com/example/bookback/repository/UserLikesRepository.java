package com.example.bookback.repository;

import com.example.bookback.entity.UserLikes;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserLikesRepository extends JpaRepository<UserLikes, Integer> {
    Optional<UserLikes> findByUser_UserSn(final Integer user_sn);
}
