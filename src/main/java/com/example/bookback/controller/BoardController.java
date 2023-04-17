package com.example.bookback.controller;

import com.example.bookback.dto.*;
import com.example.bookback.entity.Board;
import com.example.bookback.service.AuthService;
import com.example.bookback.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/board")
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;
    private final AuthService authService;

    @PostMapping("/post")
    public ResponseEntity<BoardResponseDto> PostBoard(@RequestBody BoardPostRequestDto postRequestDto){
        TokenRequestDto tokenRequestDto = new TokenRequestDto(postRequestDto.getWriter_token());
        Integer user_sn = authService.getInfo(tokenRequestDto).getUser_sn();
        return ResponseEntity.ok(boardService.post(postRequestDto, user_sn));
    }
    @PutMapping("/detail/hit")
    public void Hit(@RequestBody HitUpdateRequestDto board_update){
        boardService.updateHits(board_update.getId());
    }
    @PutMapping("/detail/recommend")
    public Boolean UpdateRecommend(@RequestBody RecOrLikeRequestDto rec_update){
        System.out.println(rec_update);
        TokenRequestDto tokenRequestDto = new TokenRequestDto(rec_update.getWriter_token());
        Integer user_sn = authService.getInfo(tokenRequestDto).getUser_sn();
        return boardService.updateRecommend(rec_update.getPost_id(), user_sn);
    }
    @GetMapping("/")
    public List<Board> getAllBoardList(){
        return boardService.getAllBoard();
    }

    @GetMapping("/hot")
    public List<Board> getHotBoardList(){ return boardService.getHotBoard(); }
    @GetMapping("/detail/")
    public ResponseEntity<BoardResponseDto> getBoardDetail(@PathVariable("id") Integer board_id){
        return ResponseEntity.ok(boardService.getBoard(board_id));
    }

}
