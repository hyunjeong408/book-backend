package com.example.bookback.repository;

import com.example.bookback.dto.MemberResponseDto;
import com.example.bookback.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Integer> {
    Optional<Member> findByUserEmailAndUserPw(final String user_email, final String user_pw);
    Optional<Member> findByUserEmail(final String user_email);
    boolean existsByUserEmail(String user_email);
    Optional<Member> findByUserSn(Integer user_sn);

}
