package com.example.querydsl;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class BoardQueryRepositoryTest {

    @Autowired
    BoardQueryRepository boardQueryRepository;

    @Test
    void findAll() {
        boardQueryRepository.findAll();
    }
}