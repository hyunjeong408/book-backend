package com.example.bookback.controller;

import com.example.bookback.dto.BoardPostRequestDto;
import com.example.bookback.dto.BoardResponseDto;
import com.example.bookback.dto.TokenRequestDto;
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
        System.out.println(postRequestDto.getContent());
        TokenRequestDto tokenRequestDto = new TokenRequestDto(postRequestDto.getWriter_token());
        Integer user_sn = authService.getInfo(tokenRequestDto).getUser_sn();
        return ResponseEntity.ok(boardService.post(postRequestDto, user_sn));
    }

    @GetMapping("/")
    public List<Board> getAllBoardList(){
        return boardService.getAllBoard();
    }

}
