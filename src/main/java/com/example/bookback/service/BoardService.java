package com.example.bookback.service;

import com.example.bookback.dto.BoardDto;
import com.example.bookback.entity.Board;
import com.example.bookback.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BoardService {
    @Autowired
    private BoardRepository boardRepository;

//    public BoardDto registerPost(BoardDto boardDto) {
//        Board newBoardPost = new Board();
//        newBoardPost.setTitle(postDto.getTitle());
//        newPost.setBody(postDto.getBody());
//        newPost.setUser(new User(1L)); // temporary code
//        return new PostDto(postRepository.saveAndFlush(newPost));
//    }

}
