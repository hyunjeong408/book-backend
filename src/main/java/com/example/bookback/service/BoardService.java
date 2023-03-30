package com.example.bookback.service;

import com.example.bookback.dto.BoardPostRequestDto;
import com.example.bookback.dto.BoardResponseDto;
import com.example.bookback.entity.Board;
import com.example.bookback.entity.Member;
import com.example.bookback.repository.BoardRepository;
import com.example.bookback.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class BoardService {
    private final BoardRepository boardRepository;

    private final MemberRepository memberRepository;

    public BoardResponseDto post(BoardPostRequestDto requestDto, Integer user_sn){
        Member writer = memberRepository.findByUserSn(user_sn).orElseThrow(()->new UsernameNotFoundException("이용자를 DB에서 찾을 수 없습니다"));
        Board board = Board.builder().title(requestDto.getTitle())
                .content(requestDto.getContent())
                .writer(writer)
                .updateDate(Timestamp.valueOf(LocalDateTime.now()))
                .build();
        return new BoardResponseDto(boardRepository.save(board));
    }

    public List<Board> getAllBoard(){
        return boardRepository.findAllByOrderByUpdateDateDesc();
    }


}
