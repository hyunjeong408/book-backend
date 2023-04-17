package com.example.bookback.service;

import com.example.bookback.dto.BoardPostRequestDto;
import com.example.bookback.dto.BoardResponseDto;
import com.example.bookback.entity.Board;
import com.example.bookback.entity.Member;
import com.example.bookback.entity.Recommend;
import com.example.bookback.repository.BoardRepository;
import com.example.bookback.repository.MemberRepository;
import com.example.bookback.repository.RecRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class BoardService {
    private final BoardRepository boardRepository;

    private final MemberRepository memberRepository;
    private final RecRepository recRepository;

    public BoardResponseDto post(BoardPostRequestDto requestDto, Integer user_sn){
        Member writer = memberRepository.findByUserSn(user_sn).orElseThrow(()->new UsernameNotFoundException("이용자를 DB에서 찾을 수 없습니다"));
        Board board = Board.builder().title(requestDto.getTitle())
                .content(requestDto.getContent())
                .writer(writer)
                .updateDate(Timestamp.valueOf(LocalDateTime.now()))
                .build();
        return new BoardResponseDto(boardRepository.save(board));
    }

    @Transactional
    public void updateHits(Integer board_id){
        Board board = boardRepository.findById(board_id).orElseThrow();
        board.updateHits();
    }

    @Transactional
    public Boolean updateRecommend(Integer board_id, Integer user_sn){
        Board board = boardRepository.findById(board_id).orElseThrow();
        Member user = memberRepository.findByUserSn(user_sn).orElseThrow(()->new UsernameNotFoundException("이용자를 DB에서 찾을 수 없습니다"));
        if (recRepository.findByBoard_IdAndUser_UserSn(board_id, user_sn) == null) { //추천하지 않은 경우
            Recommend newRec = Recommend.builder()
                    .board(board)
                    .user(user)
                    .build();
            recRepository.save(newRec);
            board.updateRecNum();
            return true;
        }
        else {
            return false;
        }
    }

    public List<Board> getAllBoard(){
        return boardRepository.findAllByOrderByUpdateDateDesc();
    }

    public BoardResponseDto getBoard(Integer boardId){
        Optional<Board> boardOptional = boardRepository.findById(boardId);
        Board board = boardOptional.get();
        return new BoardResponseDto(board);

    }

    public List<Board> getHotBoard() { return boardRepository.findTop10ByOrderByRecNumDesc(); }


}
