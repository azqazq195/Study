package com.example.thymeleaf.service;

import com.example.thymeleaf.model.Board;
import com.example.thymeleaf.model.User;
import com.example.thymeleaf.repository.BoardRepository;
import com.example.thymeleaf.repository.UserRepository;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class BoardService {
    private BoardRepository boardRepository;
    private UserRepository userRepository;

    public Board save(String username, Board board) {
        User user = userRepository.findByUsername(username);
        board.setUser(user);
        return boardRepository.save(board);
    }
}
