package com.example.bookback.controller;

import com.example.bookback.dto.BoardDto;
import com.example.bookback.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/board")
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

//    @PostMapping("/posts")
//    public ResponseEntity<BoardDto> registerPost(@RequestBody BoardDto boardDto){
//        if (boardDto.getId() != null) {
//            throw new RuntimeException("A new post cannot already have an ID");
//        } else {
//            BoardDto returnPost = boardService.registerPost(boardDto);
//            return new ResponseEntity<BoardDto>(returnPost, HttpStatus.CREATED);
//        }
//    }

}
