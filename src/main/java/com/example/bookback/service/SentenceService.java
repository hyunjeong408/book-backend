package com.example.bookback.service;

import com.example.bookback.dto.SentencePostRequestDto;
import com.example.bookback.dto.SentenceResponseDto;
import com.example.bookback.entity.Hashtag;
import com.example.bookback.entity.Member;
import com.example.bookback.entity.Sentence;
import com.example.bookback.repository.BoardRepository;
import com.example.bookback.repository.HashtagRepository;
import com.example.bookback.repository.MemberRepository;
import com.example.bookback.repository.SentenceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class SentenceService {
    private final MemberRepository memberRepository;
    private final HashtagRepository tagRepository;
    private final SentenceRepository sentenceRepository;

    public SentenceResponseDto post(SentencePostRequestDto requestDto, Integer user_sn, Integer tag_id){
        Member writer = memberRepository.findByUserSn(user_sn).orElseThrow(()->new UsernameNotFoundException("이용자를 DB에서 찾을 수 없습니다"));
        Hashtag tag = tagRepository.findByTagId(tag_id).orElseThrow();
        Sentence sentence = Sentence.builder()
                .content(requestDto.getContent())
                .writer(writer)
                .title(requestDto.getBookTitle())
                .b_writer(requestDto.getBookWriter())
                .tag(tag)
                .updateDate(Timestamp.valueOf(LocalDateTime.now()))
                .build();
        return new SentenceResponseDto(sentenceRepository.save(sentence));
    }
    public List<Sentence> getAllSentences(){
        return sentenceRepository.findAllByOrderByUpdateDateDesc();
    }
}
